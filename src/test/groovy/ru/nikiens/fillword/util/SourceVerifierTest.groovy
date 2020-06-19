package ru.nikiens.fillword.util

import ru.nikiens.fillword.model.BoardSize
import ru.nikiens.fillword.model.util.IllegalSourceFormatException
import ru.nikiens.fillword.model.util.SourceVerifier

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

class SourceVerifierTest extends Specification {
    static def SOURCES_DIR = Paths.get("src", "test", "resources")
    static def BOARD_SIZE = BoardSize.SMALL

    def cleanupSpec() {
        Files.list(SOURCES_DIR)
                .filter {it != SOURCES_DIR.resolve("testing.txt")}
                .forEach { Files.delete(it) }
    }

    def 'Throws exception when a source contains repeated words'() {
        setup:
            def file = SOURCES_DIR.resolve("duplicates.txt")

            file.withWriter { w ->
                w.writeLine 'Test'
                (BOARD_SIZE.value() / 2).times {
                    w.writeLine 'abc'
                }
            }
        when:
            new SourceVerifier(file).verify(BOARD_SIZE)
        then:
            def e = thrown(IllegalSourceFormatException)
            e.message == 'Repeated words are not allowed'
        }

    def 'Throws exception if count of words is lesser than BOARD_SIZE / 2'() {
        setup:
            def file = SOURCES_DIR.resolve("words")

            file.withWriter { w ->
                w.writeLine 'Test'
                (BOARD_SIZE.value() / 3).times {
                    w.writeLine 'abc' + (char) (67 + it)
                }
            }
        when:
            new SourceVerifier(file).verify(BOARD_SIZE)
        then:
            def e = thrown(IllegalSourceFormatException)
            e.message == 'Too few words'
    }

    def 'Throws exception when incorrect word format'() {
        setup:
            def file = SOURCES_DIR.resolve("incorrect")

            file.withWriter { w ->
                w.writeLine 'Test'
                (BOARD_SIZE.value() / 2).times {
                   w.writeLine 'abc' + (char) (9 + it)
              }
        }
        when:
            new SourceVerifier(file).verify(BOARD_SIZE)
        then:
            def e = thrown(IllegalSourceFormatException)
            e.message != 'Too few words'
            e.message != 'Repeated words are not allowed'
    }

    def 'No exceptions on correct file'() {
        setup:
            def file = SOURCES_DIR.resolve("words")

            file.withWriter { w ->
                w.writeLine 'Test'
                (BOARD_SIZE.value() / 2).times {
                    w.writeLine 'abc' + (char) (67 + it)
                }
            }
        when:
            new SourceVerifier(file).verify(BOARD_SIZE)
        then:
            noExceptionThrown()
    }
}