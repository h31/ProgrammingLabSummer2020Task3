package Model;

public class FirstLevel extends Level {
    /**
     * Конструктор - создание объекта первого уровня.
     * Передает все данные уровня в родительский (абстрактный) класс
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

    }

    /**
     * Метод отслеживающий расположение игрока относительно объектов на карте
     * @see LevelObject
     * Позволяет игроку находиться перед и за объектом.
     * @param player - объект игрока
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

    @Override
    void reload() {

    }
}
