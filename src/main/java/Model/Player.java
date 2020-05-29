package Model;

import View.View;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player {

    private final ImageView[] SKELETON_IDLE_RIGHT;
    private final ImageView[] SKELETON_IDLE_LEFT;
    private final ImageView[] SKELETON_WALK_RIGHT;
    private final ImageView[] SKELETON_WALK_LEFT;

    {
        SKELETON_IDLE_RIGHT = SpriteData.getSprites("Skeleton_Idle_Right");
        SKELETON_IDLE_LEFT = SpriteData.getSprites("Skeleton_Idle_Left");
        SKELETON_WALK_RIGHT = SpriteData.getSprites("Skeleton_Walk_Right");
        SKELETON_WALK_LEFT = SpriteData.getSprites("Skeleton_Walk_Left");
    }

    private Status action = Status.IDLE; // Текущая анимация
    private Status.View view = Status.View.LEFT; // Куда смотрит сейчас
    private ImageView imgView = SKELETON_IDLE_LEFT[0]; // Как выглядит сейчас
    private ImageView[] imgArray = SKELETON_IDLE_LEFT; // Массив кадров
    private final Rectangle COLLISION;
    private double BOTTOM_COLLISION;
    private final Level level;

    private double velY = 0;
    private double velX = 0;
    public final double SPEED = 1.2;
    private boolean freezed = false;

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

    public Player(View view, Level level, int x, int y) {
        this.VIEW = view;
        this.level = level;
        this.imgView.setX(x);
        this.imgView.setY(y);
        this.COLLISION = new Rectangle(x, y, imgView.getImage().getWidth(), imgView.getImage().getHeight());
        this.COLLISION.setFill(Color.BLUE);
        this.COLLISION.setOpacity(0);
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
        for (Rectangle colShape : level.getTRIGGERS()) {
            if (this.isFreezed()) return false;
            if (getCOLLISION().intersects(colShape.getBoundsInLocal())) {
                this.setFreezed(true);
                FadeTransition ft = new FadeTransition(Duration.millis(1000), this.getImgView());
                ft.setFromValue(1.0);
                ft.setToValue(0);
                ft.setCycleCount(1);
                ft.setOnFinished(actionEvent -> {
                    if (level.getLocation().equals("First")) {
                        level.setLocation("Start");
                    } else if (level.getLocation().equals("Start")) {
                        level.setLocation("First");
                    }
                    VIEW.showScene();
                    this.setFreezed(false);
                    this.getImgView().setOpacity(1);
                    System.out.println("Trigger");
                });
                ft.play();
            }
        }
        return false;
    }

    public Status.View getView() {
        return this.view;
    }

    public void setView(Status.View newView) {
        this.view = newView;
    }


    public ImageView getImgView() {
        return this.imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public ImageView[] getImgArray() {
        return this.imgArray;
    }

    public void setImgArray(ImageView[] imgArray) {
        this.imgArray = imgArray;
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
