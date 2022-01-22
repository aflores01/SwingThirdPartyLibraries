import com.alexandriasoftware.swing.JInputValidator;
import com.alexandriasoftware.swing.JInputValidatorPreferences;
import com.alexandriasoftware.swing.Validation;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import de.milchreis.uibooster.UiBooster;
import de.milchreis.uibooster.components.Splashscreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class Main extends JFrame {

    public Splashscreen splashscreen;
    private JButton notificationButton;
    private JButton showSplash;
    private JButton galleryButton;

    public Main() {
        UiBooster booster = new UiBooster();
        booster.createTrayMenu("Reto 3", "src\\resources\\icon.png")
            .withPopupMenu()
            .addMenu("Acerca de", () -> booster.showInfoDialog("19019192 \n Angel Flores Cortes"));

        ListenButton listenButton = new ListenButton();
        UIManager.put("Button.arc", 999);
        UIManager.put("TabbedPane.showTabSeparators", true);
        UIManager.put("Component.arrowType", "chevron");
        UIManager.put("Component.focusWidth", 3);

        setTitle("Third party libraries");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JButton exampleButton = new JButton();
        exampleButton.setText("Example FlatLaf round corner style Button");
        exampleButton.setBounds(10, 10, 300, 80);

        showSplash = new JButton("Show SplashScreen!");
        showSplash.addActionListener(listenButton);

        notificationButton = new JButton("Show Notification");
        notificationButton.addActionListener(listenButton);

        galleryButton = new JButton("Abrir galería");
        galleryButton.addActionListener(listenButton);

        JComboBox<String> options = new JComboBox<>();
        options.addItem("Chevron combo box style");
        options.addItem("Outer Focus customization");
        options.addItem("3");

        JLabel warningLabel = new JLabel("Warning validation:");
        JLabel informationLabel = new JLabel("Information helper:");
        JLabel successLabel = new JLabel("Success validation");
        JLabel dangerLabel = new JLabel("Danger validation:");
        JLabel unknownLabel = new JLabel("Unknown helper");

        JTextField errorTextField = new JTextField();
        errorTextField.setInputVerifier(
            new JInputValidator(errorTextField) {
                @Override
                protected Validation getValidation(JComponent jComponent, JInputValidatorPreferences preferences) {
                    if (errorTextField.getText().length() < 8) {
                        return new Validation(Validation.Type.WARNING, "Texto demasiado corto", preferences);
                    }
                    return new Validation(Validation.Type.NONE, "", preferences);
                }
            }
        );

        JTextField informationTextField = new JTextField();
        informationTextField.setInputVerifier(
            new JInputValidator(informationTextField) {
                @Override
                protected Validation getValidation(JComponent jComponent, JInputValidatorPreferences preferences) {
                    return new Validation(Validation.Type.INFORMATION, "Cualquier texto es permitido", preferences);
                }
            }
        );

        JTextField successTextField = new JTextField();
        successTextField.setInputVerifier(
            new JInputValidator(successTextField) {
                @Override
                protected Validation getValidation(JComponent jComponent, JInputValidatorPreferences preferences) {
                    if (!successTextField.getText().isEmpty()) {
                        return new Validation(Validation.Type.SUCCESS, "Correcto", preferences);
                    }
                    return new Validation(Validation.Type.NONE, "", preferences);
                }
            }
        );

        JTextField dangerTextField = new JTextField();
        dangerTextField.setInputVerifier(
            new JInputValidator(dangerTextField) {
                @Override
                protected Validation getValidation(JComponent jComponent, JInputValidatorPreferences preferences) {
                    if (!dangerTextField.getText().isEmpty()) {
                        return new Validation(Validation.Type.NONE, "", preferences);
                    }
                    return new Validation(Validation.Type.DANGER, "Este campo debe estar vacío!", preferences);
                }
            }
        );

        JTextField unknownTextField = new JTextField();
        unknownTextField.setInputVerifier(
            new JInputValidator(unknownTextField) {
                @Override
                protected Validation getValidation(JComponent jComponent, JInputValidatorPreferences preferences) {
                    if (unknownTextField.getText().matches("/^((?:\\*|[0-5]?[0-9](?:(?:-[0-5]?[0-9])|(?:,[0-5]?[0-9])+)?)(?:\\/[0-9]+)?)\\s+((?:\\*|(?:1?[0-9]|2[0-3])(?:(?:-(?:1?[0-9]|2[0-3]))|(?:,(?:1?[0-9]|2[0-3]))+)?)(?:\\/[0-9]+)?)\\s+((?:\\*|(?:[1-9]|[1-2][0-9]|3[0-1])(?:(?:-(?:[1-9]|[1-2][0-9]|3[0-1]))|(?:,(?:[1-9]|[1-2][0-9]|3[0-1]))+)?)(?:\\/[0-9]+)?)\\s+((?:\\*|(?:[1-9]|1[0-2])(?:(?:-(?:[1-9]|1[0-2]))|(?:,(?:[1-9]|1[0-2]))+)?)(?:\\/[0-9]+)?)\\s+((?:\\*|[0-7](?:-[0-7]|(?:,[0-7])+)?)(?:\\/[0-9]+)?)$/gm")) {
                        return new Validation(Validation.Type.SUCCESS, "Correcto", preferences);
                    }
                    return new Validation(Validation.Type.UNKNOWN, "Sólo admite expresiones CRON", preferences);
                }
            }
        );


        JPanel panelTabOne = new JPanel();
        panelTabOne.add(exampleButton);
        panelTabOne.add(options);

        JPanel panelTabTwo = new JPanel();
        panelTabTwo.add(showSplash);
        panelTabTwo.add(notificationButton);
        panelTabTwo.add(galleryButton);

        JPanel panelTabThree = new JPanel();
        panelTabThree.add(warningLabel);
        panelTabThree.add(errorTextField);
        panelTabThree.add(informationLabel);
        panelTabThree.add(informationTextField);
        panelTabThree.add(successLabel);
        panelTabThree.add(successTextField);
        panelTabThree.add(dangerLabel);
        panelTabThree.add(dangerTextField);
        panelTabThree.add(unknownLabel);
        panelTabThree.add(unknownTextField);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("FlatLaf 2.0", panelTabOne);
        tabbedPane.addTab("UiBooster 1.14.1", panelTabTwo);
        tabbedPane.addTab("JInputValidator", panelTabThree);

        getContentPane().add(tabbedPane);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup(new FlatDarculaLaf());
        new Main();
    }

    class ListenButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showSplash) {
                splashscreen = new UiBooster().showSplashscreen("src\\resources\\splash.png");
                splashscreen.show(3000);
                System.out.println("Showing splash screen for 3000ms");
            } else if (e.getSource() == notificationButton) {
                new UiBooster().createNotification("Tópicos avanzados de programación", "Reto 3");
            } else if (e.getSource() == galleryButton) {
                new UiBooster().showPictures("Galería", Arrays.asList(
                        new File("src/resources/icon.png"),
                        new File("src/resources/splash.png")
                ));
            }
        }
    }
}
