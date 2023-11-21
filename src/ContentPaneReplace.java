import javax.swing.*;
public class ContentPaneReplace extends JFrame{
    ContentPaneReplace()
    {
        super("Test ContentPane");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Создание панели с двумя кнопками
        JPanel contents = new JPanel();
        contents.add(new JButton("Семья"));
        contents.add(new JButton("Школа"));
        // Замена панели содержимого
        setContentPane(contents);

        // Определение размера окна
        setSize(200, 100);
        // Открытие окна
        setVisible(true);
    }

    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new ContentPaneReplace();
    }
}
