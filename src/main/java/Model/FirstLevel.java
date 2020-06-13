package Model;
import java.util.ArrayList;
import java.util.LinkedList;

public class FirstLevel extends Level {

    public FirstLevel() {
        super(LEVEL_CONTANTS.FIRST_LOCATION,
                LEVEL_CONTANTS.FIRST_IMG,
                new LinkedList<>(LEVEL_CONTANTS.FIRST_COLLISION),
                new ArrayList<>(LEVEL_CONTANTS.FIRST_TRIGGERS),
                LEVEL_CONTANTS.FIRST_OBJECTS.clone(),
                LEVEL_CONTANTS.FIRST_pCOORD
        );
    }

    @Override
    void interact(Trigger trigger) {

    }

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
