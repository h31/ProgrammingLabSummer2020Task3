package Model;

import Controller.Controller;
import javafx.scene.image.ImageView;

public class FirstLevel extends Level {
    /**
     * Конструктор - создание объекта первого уровня.
     * Передает все данные уровня в родительский (абстрактный) класс
     *
     * @see Level
     */
    public FirstLevel() {
        super(LEVEL_CONTANTS.FIRST_LOCATION,
                LEVEL_CONTANTS.FIRST_IMG,
                LEVEL_CONTANTS.FIRST_COLLISION,
                LEVEL_CONTANTS.FIRST_TRIGGERS,
                LEVEL_CONTANTS.FIRST_OBJECTS,
                LEVEL_CONTANTS.FIRST_pCOORD
        );
    }

    @Override
    void interact(Trigger trigger) {
        if (trigger.getNAME().equals("ReadNote")) {
            readNote();
        }
    }

    /**
     * Метод отслеживающий расположение игрока относительно объектов на карте
     *
     * @param player - объект игрока
     * @see LevelObject
     * Позволяет игроку находиться перед и за объектом.
     */
    public void checkObjectView(Player player) {
        double playerCol = player.getBOTTOM_COLLISION();
        for (LevelObject colShape : getOBJECTS()) {
            if (playerCol > colShape.getIMG_VIEW().getY() + colShape.getIMG_VIEW().getImage().getHeight()) {
                colShape.setView(OBJECT_VIEW.BACK);
            } else {
                colShape.setView(OBJECT_VIEW.FRONT);
            }
        }
    }

    void readNote() {
        Player.reading = getTRIGGERS().get(1).getIMAGE();
        Player.reading.setVisible(true);
    }

    @Override
    void reload() {

    }
}
