package Model;

import Controller.Controller;
import View.View;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Player extends Animated {

    private final ImageView[] SKELETON_IDLE_RIGHT;
    private final ImageView[] SKELETON_IDLE_LEFT;
    private final ImageView[] SKELETON_WALK_RIGHT;
    private final ImageView[] SKELETON_WALK_LEFT;

    private Status action = Status.IDLE; // Текущая анимация
    private Status.View view = Status.View.LEFT; // Куда смотрит сейчас
    private final Rectangle COLLISION;
    private double BOTTOM_COLLISION;

    private Level level;

    private double velY = 0;
    private double velX = 0;
    public final double SPEED = 1.25;
    static boolean freezed;
    static ImageView reading;

    private final View VIEW;

    private final AnimationTimer movementAnim = new AnimationTimer() {
        @Override
        public void handle(long l) {
            // Коллизия проверяет местоположение игрока наперед, чтобы не попасть на стенку.
            double posX = getImgView().getX() + getVelX();
            double posY = getImgView().getY() + getVelY();
            getCOLLISION().setX(posX);
            getCOLLISION().setY(posY);
            if (isCollision()) {
                return;
            }
            move(posX, posY);
            getCOLLISION().setX(getImgView().getX());
            getCOLLISION().setY(getImgView().getY());
            setBOTTOM_COLLISION(getCOLLISION().getHeight() + getCOLLISION().getY());
        }
    };

    public Player(View view, Level level) {
        SKELETON_IDLE_RIGHT = SpriteData.getSprites("Player/Skeleton_Idle_Right");
        SKELETON_IDLE_LEFT = SpriteData.getSprites("Player/Skeleton_Idle_Left");
        SKELETON_WALK_RIGHT = SpriteData.getSprites("Player/Skeleton_Walk_Right");
        SKELETON_WALK_LEFT = SpriteData.getSprites("Player/Skeleton_Walk_Left");
        super.setImgArray(SKELETON_IDLE_LEFT);
        super.setImgView(SKELETON_IDLE_LEFT[0]);
        this.VIEW = view;
        this.level = level;
        super.getImgView().setX(level.getPCoord()[0]);
        super.getImgView().setY(level.getPCoord()[1]);
        this.COLLISION = new Rectangle(super.getImgView().getX(), super.getImgView().getY(), super.getImgView().getImage().getWidth(), super.getImgView().getImage().getHeight());
        this.BOTTOM_COLLISION = this.COLLISION.getY() + this.COLLISION.getHeight(); // Получаем координаты по Y нижней части коллизии
        runAnimation();
    }


    private void runAnimation() {
        final Animation animation = new SpriteAnimation(
                Duration.millis(500),
                this
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    private ImageView[] getSKELETON_IDLE(Status.View view) {
        if (view == Status.View.RIGHT) {
            return SKELETON_IDLE_RIGHT;
        } else {
            return SKELETON_IDLE_LEFT;
        }
    }


    private ImageView[] getSKELETON_WALK(Status.View view) {
        if (view == Status.View.RIGHT) {
            return SKELETON_WALK_RIGHT;
        } else {
            return SKELETON_WALK_LEFT;
        }
    }

    public void setPosition(double x, double y) {
        getCOLLISION().setX(x);
        getCOLLISION().setY(y);
        getImgView().setX(x);
        getImgView().setY(y);
    }

    //Передвижение на экране
    private void move(double posX, double posY) {
        level.checkObjectView(this);
        View.movePlayer(this, posX, posY);
    }

    //Анимация передвижения
    public void startWalkAnim() {
        getMovementAnim().start();
    }

    private AnimationTimer getMovementAnim() {
        return movementAnim;
    }


    public Status getAction() {
        return this.action;
    }

    public void setAction(Status action, Status.View view) {
        if (action == Status.IDLE) {
            setImgArray(getSKELETON_IDLE(view));
        }
        if (action == Status.WALK) {
            setImgArray(getSKELETON_WALK(view));
        }
        setView(view);
    }

    boolean isCollision() {
        return isWallCollision() || isObjectCollision() || isTriggerCollision();
    }

    private boolean isWallCollision() {
        for (Rectangle colShape : level.getCOLLISION()) {
            if (getCOLLISION().intersects(colShape.getBoundsInLocal())) {
                return true;
            }
        }
        return false;
    }

    boolean isObjectCollision() {
        for (LevelObject object : level.getOBJECTS()) {
            if (getCOLLISION().intersects(object.getCurrentCollision().getBoundsInLocal())) {
                return true;
            }
        }
        return false;
    }

    private boolean isTriggerCollision() {
        for (Trigger trigger : level.getTRIGGERS()) {
            if (this.isFreezed()) return false;
            Rectangle rect = trigger.getRECT();
            if (getCOLLISION().intersects(rect.getBoundsInLocal())) {
                if (trigger.getUsed()) return false;
                if (trigger.getTYPE() == COLLISION_TYPE.ENTER) {
                    changeLocation();
                } else if (trigger.getTYPE() == COLLISION_TYPE.INTERACT && Controller.keyState[4]) {
                    setFreezed(true);
                    level.interact(trigger);
                    setFreezed(false);
                } else if (trigger.getTYPE() == COLLISION_TYPE.COLIDED) {
                    level.interact(trigger);
                } else if (trigger.getTYPE() == COLLISION_TYPE.DEATH) {
                    System.out.println("You are dead");
                    die();
                }
            }
        }
        return false;
    }

    public void closeNote() {
        if (reading == null) return;
        reading.setVisible(false);
    }

    private void changeLocation() {
        this.setFreezed(true);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this.getImgView());
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        onChangeLocationFinish(ft);
        ft.play();
    }

    private void onChangeLocationFinish(FadeTransition fadeTransition) {
        fadeTransition.setOnFinished(actionEvent -> {
            if (level.getLocation().equals("First")) {
                level = new SecondLevel();
            } else if (level.getLocation().equals("Second")) {
                level = new FirstLevel();
            } else {
                throw new IllegalArgumentException("Error");
            }
            int[] newCoord = level.getPCoord();
            this.setPosition(newCoord[0], newCoord[1]);
            VIEW.setLEVEL(level);
            VIEW.showScene();
            this.setFreezed(false);
            this.getImgView().setOpacity(1);
        });
    }

    private void die() {
        this.setFreezed(true);
        FadeTransition ft = new FadeTransition(Duration.millis(100), this.getImgView());
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        onDieFinish(ft);
        ft.play();
    }

    private void onDieFinish(FadeTransition fadeTransition) {
        fadeTransition.setOnFinished(actionEvent -> {
            if (level.getLocation().equals("First")) {
                level.reload();
            } else if (level.getLocation().equals("Second")) {
                level.reload();
            } else {
                throw new IllegalArgumentException("Error");
            }
            int[] newCoord = level.getPCoord();
            this.setPosition(newCoord[0], newCoord[1]);
            VIEW.setLEVEL(level);
            VIEW.showScene();
            this.setFreezed(false);
            this.getImgView().setOpacity(1);
        });
    }

    public Status.View getView() {
        return this.view;
    }

    private void setView(Status.View newView) {
        this.view = newView;
    }


    public ImageView getImgView() {
        return super.getImgView();
    }

    public void setImgView(ImageView imgView) {
        super.setImgView(imgView);
    }

    public ImageView[] getImgArray() {
        return super.getImgArray();
    }

    public void setImgArray(ImageView[] imgArray) {
        super.setImgArray(imgArray);
        getImgView().setImage(imgArray[0].getImage());
    }

    public Rectangle getCOLLISION() {
        return COLLISION;
    }


    private double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    private double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public Level getLevel() {
        return level;
    }

    private boolean isFreezed() {
        return freezed;
    }

    private void setFreezed(boolean freezed) {
        Player.freezed = freezed;
    }

    double getBOTTOM_COLLISION() {
        return BOTTOM_COLLISION;
    }

    void setBOTTOM_COLLISION(double BOTTOM_COLLISION) {
        this.BOTTOM_COLLISION = BOTTOM_COLLISION;
    }
}
