package goat_cabbage;

import goat_cabbage.model.Game;
import goat_cabbage.model.GameStatus;
import goat_cabbage.model.event.GameActionEvent;
import goat_cabbage.model.event.GameActionListener;
import goat_cabbage.model.field.cell_objects.Goat;
import goat_cabbage.model.labirint.SmallLabirint;
import goat_cabbage.ui.FieldWidget;
import goat_cabbage.ui.WidgetFactory;
import goat_cabbage.ui.cell.GoatWidget;
import goat_cabbage.ui.utils.GameWidgetsUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GamePanel::new);
    }

    static class GamePanel extends JFrame {
        private Game game;
        private WidgetFactory widgetFactory;

        public GamePanel() throws HeadlessException {
            setVisible(true);
            startGame();
            setResizable(false);

            JMenuBar menuBar = new JMenuBar();
            menuBar.add(createGameMenu());
            setJMenuBar(menuBar);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        private JMenu createGameMenu() {
            JMenu gameMenu = new JMenu("Игра");
            JMenuItem newGameMenuItem = new JMenuItem(new NewGameAction());
            gameMenu.add(newGameMenuItem);
            return gameMenu;
        }

        private void startGame() {
            widgetFactory = new WidgetFactory();
            game = new Game(new SmallLabirint());

            game.addGameActionListener(new GameController());

            JPanel content = (JPanel) this.getContentPane();
            content.removeAll();
            content.add(new FieldWidget(game.getGameField(), widgetFactory));
            System.out.println(game.getGoat());
            System.out.println(widgetFactory.getWidget(game.getGoat()));
            widgetFactory.getWidget(game.getGoat()).requestFocus();

            pack();
        }

        private class NewGameAction extends AbstractAction {
            public NewGameAction() {
                putValue(NAME, "Новая");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(GamePanel.this,
                        "Начать новую игру?", "Новая игра",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) startGame();
            }
        }

        private final class GameController implements GameActionListener {
            @Override
            public void goatIsMoved(@NotNull GameActionEvent event) {
                // No implements yet.
            }

            @Override
            public void gameStatusChanged(@NotNull GameActionEvent event) {
                GameStatus status = event.getStatus();
                if(status != GameStatus.GAME_IS_ON) {
                    String message = "";
                    switch (status) {
                        case WIN:
                            message = "Выйграла коза";
                            break;
                        case GAME_ABORTED:
                            message= "Игра завершена досрочно";
                            break;
                        case GOAT_NOT_HAVE_MOVES:
                            message = "Все роботы имеют нулевой заряд";
                            break;
                    }
                    String[] options = {"ok"};
                    int value = JOptionPane.showOptionDialog(GamePanel.this, message, "Игра окончена", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if(value == 0 || value == 1) {
                        startGame();
                        GamePanel.this.repaint();
                    }
                }
            }
        }
    }
}
