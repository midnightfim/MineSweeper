import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {

    private Game game;

    private JPanel panel;  // переменная для создания панели
    private JLabel label;

    private final int COLS = 9;  // колличество строчек
    private final int ROWS = 9;   // колличество столбцов
    private final int BOMBS = 10;  // колличество бомб на поле
    private final int IMAGE_SIZE = 50;   // размер иконок

    public static void main(String[] args) {
        new JavaSweeper().setVisible(true);  // инициализилуем класс JFrame
    }

    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {     // автоматическая функция, которая рисует графику
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords())//  перебираем все координаты
                {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);

                }
            }
        };  // создаем панелель
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) // левая кнопка мышки
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)  // правая кнопка мышки
                    game.pressRightButton(coord);
                label.setText(getMessage());
                panel.repaint();
            } // подключаем адаптер мышки к панели
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));  // устанавливаем размер панели
        add(panel);                              // добавляем панель в приложение
    }

    private JavaSweeper(){
        game = new Game(COLS, ROWS, BOMBS);
        game.start();  // запускаем игру
        setImages();  //  подгружаем картинки
        initLabel();
        initPanel(); // запускаем панель
        initFrame(); // запускаем все общие методы при включениии
    }

    private void initLabel(){
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);

    }

    private void initFrame() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MineSweeper 2049");
        setLocationRelativeTo(null);    // центрироване плиложения при запуске
        setResizable(false);
        setVisible(true);               // выводим приложение на экран
        setIconImage(getImage("icon"));     //  иконка приложения
        pack();
    }

    private Image getImage(String name){        //  функция для получения картинок
        String filename = "img/" + name.toLowerCase() + ".png";  //   имя файла каждой картинкм
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));    // создаем объект ImageIcon
        return icon.getImage();
    }

    private void setImages(){       // метод для подгрушения картинок в Box
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private String getMessage(){
        switch (game.getState())
        {
            case PLAYED: return "Opened!";
            case BOMBED: return "Oops";
            case WINNER: return "Congratulations!";
            default: return "No";
        }
    }
}
