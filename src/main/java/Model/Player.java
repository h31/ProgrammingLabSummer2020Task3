package Model;

import Controller.Controller;
import View.View;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.LinkedList;

public class Player extends Animated {

    private final ImageView[] SKELETON_IDLE_RIGHT;
    private final ImageView[] SKELETON_IDLE_LEFT;
    private final ImageView[] SKELETON_WALK_RIGHT;
    private final ImageView[] SKELETON_WALK_LEFT;

    private Status action = Status.IDLE; // Текущая анимация
    private Status.View view = Status.View.LEFT; // Куда смотрит сейчас
    private final Rectangle COLLISION;
    private double BOTTOM_COLLISION;
    private final Level level;

    private double velY = 0;
    private double velX = 0;
    public final double SPEED = 1.2;
    public static boolean freezed = false;

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
        SKELETON_IDLE_RIGHT = SpriteData.getSprites("Skeleton_Idle_Right");
        SKELETON_IDLE_LEFT = SpriteData.getSprites("Skeleton_Idle_Left");
        SKELETON_WALK_RIGHT = SpriteData.getSprites("Skeleton_Walk_Right");
        SKELETON_WALK_LEFT = SpriteData.getSprites("Skeleton_Walk_Left");
        super.setImgArray(SKELETON_IDLE_LEFT);
        super.setImgView(SKELETON_IDLE_LEFT[0]);
        this.VIEW = view;
        this.level = level;
        super.getImgView().setX(Level.START_pCOORD[0]);
        super.getImgView().setY(Level.START_pCOORD[1]);
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

    public ImageView[] getSKELETON_IDLE(Status.View view) {
        if (view == Status.View.RIGHT) {
            return SKELETON_IDLE_RIGHT;
        } else {
            return SKELETON_IDLE_LEFT;
        }
    }


    public ImageView[] getSKELETON_WALK(Status.View view) {
        if (view == Status.View.RIGHT) {
            return SKELETON_WALK_RIGHT;
        } else {
            return SKELETON_WALK_LEFT;
        }
    }

    public void setPosition(double x, double y) {
        getImgView().setX(x);
        getImgView().setY(y);
    }

    //Передвижение на экране
    public void move(double posX, double posY) {
        level.checkObjectView(this);
        View.movePlayer(this, posX, posY);
    }

    //Анимация передвижения
    public void startWalkAnim() {
        getMovementAnim().start();
    }

    public AnimationTimer getMovementAnim() {
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

    public boolean isCollision() {
        for (Rectangle colShape : level.getCOLLISION()) {
            if (getCOLLISION().intersects(colShape.getBoundsInLocal())) {
                System.out.println("Collision");
                return true;
            }
        }
        for (LevelObject object : level.getOBJECTS()) {
            if (getCOLLISION().intersects(object.getCurrentCollision().getBoundsInLocal())) {
                System.out.println("YEAH");
                return true;
            }
        }
        for (Trigger trigger : level.getTRIGGERS()) {
            if (this.isFreezed()) return false;
            Rectangle rect = trigger.getRECT();
            if (getCOLLISION().intersects(rect.getBoundsInLocal())) {
                if (trigger.getTYPE() == COLLISION_TYPE.ENTER) {
                    changingLocation();
                } else if (trigger == level.getTRIGGERS().element() && trigger.getTYPE() == COLLISION_TYPE.INTERACT && Controller.keyState[4]) {
                    interact(level.getTRIGGERS().poll());
                }
            }
        }
        return false;
    }

    public void interact(Trigger trigger) {
        setFreezed(true);
        System.out.println("Now in interact()");
        Effect effect = trigger.getEFFECT();
        VIEW.showEffect(effect);
    }

    public void changingLocation() {
        this.setFreezed(true);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this.getImgView());
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setOnFinished(actionEvent -> {
            int[] newCoord;
            if (level.getLocation().equals("First")) {
                newCoord = level.setLocation("Start");
            } else if (level.getLocation().equals("Start")) {
                newCoord = level.setLocation("First");
            } else {
                throw new IllegalArgumentException("Error");
            }
            this.setPosition(newCoord[0], newCoord[1]);
            VIEW.showScene();
            this.setFreezed(false);
            this.getImgView().setOpacity(1);
            System.out.println("Trigger");
        });
        ft.play();
    }

    public Status.View getView() {
        return this.view;
    }

    public void setView(Status.View newView) {
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


    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public Level getLevel() {
        return level;
    }

    public boolean isFreezed() {
        return freezed;
    }

    public void setFreezed(boolean freezed) {
        this.freezed = freezed;
    }

    public double getBOTTOM_COLLISION() {
        return BOTTOM_COLLISION;
    }

    public void setBOTTOM_COLLISION(double BOTTOM_COLLISION) {
        this.BOTTOM_COLLISION = BOTTOM_COLLISION;
    }
}
