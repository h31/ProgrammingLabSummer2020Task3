package ru.nikiens.fillword

import javafx.css.PseudoClass
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

import org.testfx.framework.spock.ApplicationSpec
import org.testfx.util.WaitForAsyncUtils
import org.testfx.api.FxAssert
import org.testfx.api.FxToolkit

import ru.nikiens.fillword.model.BoardSize
import ru.nikiens.fillword.model.Cell

import ru.nikiens.fillword.model.Game

import java.nio.file.Paths

import static org.testfx.matcher.base.NodeMatchers.isVisible

class GameTest extends ApplicationSpec {
    void setup() {
        Game.getInstance().setBoardSize(BoardSize.TESTING)
        Game.getInstance().initializeCategory(Paths.get("src", "test", "resources", "testing.txt"))

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"))

        FxToolkit.registerStage { new Stage() }
        FxToolkit.setupStage { it.setScene(new Scene(root)) }
        FxToolkit.showStage()

        WaitForAsyncUtils.waitForFxEvents(100)
    }

    void start(Stage stage) {
        stage.toFront()
    }

    void stop() {
        FxToolkit.cleanupStages()
    }

    def 'Cell changes its color depending on its state'() {
        given:
            def cell = lookup('A').query()
        when: 'drag'
            drag(cell).moveTo(0.0,0.0)
        then:
            cell.getPseudoClassStates().contains(PseudoClass.getPseudoClass("selected"))
            Game.getInstance().getCell(0,0).getState() == Cell.State.UNMARKED
        when: 'drop'
            drag(cell).dropTo(cell)
        then:
            cell.getPseudoClassStates().contains(PseudoClass.getPseudoClass("marked"))
            Game.getInstance().getCell(0,0).getState() == Cell.State.MARKED
    }

    def 'ListCell with a found word changes its color'() {
        given:
            def cell = lookup('A').query()
            def wordCell = lookup('#wordCell-A').query()
        when:
            drag(cell).dropBy(50.0, 1.0)
        then:
            wordCell.getPseudoClassStates().contains(PseudoClass.getPseudoClass("selected"))

    }

    def 'Ending dialog spawns after finishing the game'() {
        given:
            def cell = lookup('A').query()
        when:
            drag(cell).dropBy(50.0,1.0)
        then:
            FxAssert.verifyThat('#endingDialog', isVisible())
    }
}