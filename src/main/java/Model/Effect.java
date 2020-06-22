package Model;

import javafx.animation.Animation;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Effect extends Animated {
    private EFFECT_TYPE TYPE;
    private Rectangle COLLISION;

    /**
     * Конструктор - создание нового объекта эффект
     *
     * @param type - тип эффекта
     * @param x    - начальная координата x для эффекта
     * @param y    - начальная координата y для эффекта
     * @see COLLISION_TYPE
     **/
    Effect(EFFECT_TYPE type, int x, int y) {
        this.TYPE = type;
        ImageView[] effect;
        double offsetX = 0, offsetY = 0, offsetWidth = 0, offsetHeight = 0;
        if (TYPE == EFFECT_TYPE.MAGIC) {
            effect = SpriteData.getSprites("Effects/vortexmagic");
        } else if (TYPE == EFFECT_TYPE.MAGIC_BALL) {
            effect = SpriteData.getSprites("Effects/MagicBall");
            offsetX = 7;
            offsetY = 7;
            offsetHeight = -15;
            offsetWidth = -15;
        } else if (TYPE == EFFECT_TYPE.MAGIC_ITEM) {
            effect = SpriteData.getSprites("Effects/Tesla_Orb");
        } else {
            throw new IllegalArgumentException();
        }
        super.setImgArray(effect);
        super.setImgView(effect[0]);
        setPosition(x, y);
        COLLISION = SpriteData.spriteToCollision(super.getImgView(), offsetX, offsetY, offsetWidth, offsetHeight);
        moveCollision();
    }

    /**
     * Пустой конструктор, используется при создании триггера, который не создает эффектов
     */
    Effect() {

    }

    /**
     * Запускает анимацию в зависимости от ее типа
     */
    public void runAnimation() {
        this.getImgView().setVisible(true);
        if (TYPE == EFFECT_TYPE.MAGIC) {
            runMagicAnim();
        }
        if (TYPE == EFFECT_TYPE.MAGIC_BALL) {
            runMagicBallAnim();
        }
        if (TYPE == EFFECT_TYPE.MAGIC_ITEM) {
            runTeslaOrbAnim();
        }
    }

    void stopAnimation() {
        if (TYPE == EFFECT_TYPE.MAGIC_ITEM) {
            LEVEL_CONTANTS.teslaOrbAnim.stop();
            setImg(LEVEL_CONTANTS.teslaOrbBroken);
            System.out.println(LEVEL_CONTANTS.teslaOrbAnim.getStatus());
        }
    }

    /**
     * Запускает спрайтовую анимацию магических частиц
     */
    private void runMagicAnim() {
        final Animation animation = new SpriteAnimation(
                Duration.millis(1500),
                this
        );
        animation.setOnFinished(actionEvent -> {
            Player.freezed = false;
            this.getImgView().setVisible(false);
        });
        animation.setCycleCount(1);
        animation.play();
    }

    /**
     * Запускает спрайтовую анимацию магического шара
     */
    private void runMagicBallAnim() {
        final Animation animation = new SpriteAnimation(
                Duration.millis(1500),
                this
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    private void runTeslaOrbAnim() {
        if (LEVEL_CONTANTS.teslaOrbAnim != null) {
            LEVEL_CONTANTS.teslaOrbAnim.play();
            return;
        }
        LEVEL_CONTANTS.teslaOrbAnim = new SpriteAnimation(
                Duration.millis(1500),
                this
        );
        LEVEL_CONTANTS.teslaOrbAnim.setCycleCount(Animation.INDEFINITE);
        LEVEL_CONTANTS.teslaOrbAnim.play();
    }

    /**
     * Метод запускающий слушателей, реагирующих на изменение координат ImageView.
     * Позволяет синхронно передвигать картинку и триггер за ней
     */
    public void moveCollision() {
        DoubleProperty xValue = new SimpleDoubleProperty();
        DoubleProperty yValue = new SimpleDoubleProperty();
        xValue.bind(getImgView().translateXProperty());
        final double defaultX = getCOLLISION().getX();
        yValue.bind(getImgView().translateYProperty());
        final double defaultY = getCOLLISION().getY();
        xValue.addListener((ov, t, t1) -> getCOLLISION().setX(defaultX + (double) t1));
        yValue.addListener((ov, t, t1) -> getCOLLISION().setY(defaultY + (double) t1));
    }

    public boolean isEmpty() {
        return TYPE == null && COLLISION == null;
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

    private void setPosition(int x, int y) {
        super.getImgView().setX(x);
        super.getImgView().setY(y);
    }

    Rectangle getCOLLISION() {
        return COLLISION;
    }
}

