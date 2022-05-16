import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.Objects;
import java.util.Random;

public class ActivityFrame extends JFrame {
    private JComboBox<String> comboMenu;
    private JButton submitButton;
    private JLabel output;
    private JSpinner numPeople;
    private JButton btnLink;
    private final String[] categories = {"education", "recreational", "social",
            "diy", "charity", "cooking", "relaxation", "music", "busywork"};
    private String url;
    ActivityPresenter presenter;


    public ActivityFrame() {
        setTitle("Activity Generator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        add(horizontalPanel);

        comboMenu = new JComboBox<>(categories);
        horizontalPanel.add(comboMenu);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 5, 1);
        numPeople = new JSpinner(spinnerModel);
        horizontalPanel.add(numPeople);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(4, 1));
        add(grid);

        submitButton = new JButton();
        submitButton.setText("Click for an activity");
        submitButton.addActionListener(this::onSubmitClicked);
        grid.add(submitButton);


        output = new JLabel();
        output.setText("Let's get started!");
        grid.add(output);

        btnLink = new JButton();
        grid.add(btnLink);
        btnLink.addActionListener(this::onClickLink);


        GetActivityServiceFactory factory = new GetActivityServiceFactory();
        presenter = new ActivityPresenter(this, factory.getInstance());

    }


    private void onClickLink(ActionEvent event) {
        try
        {
            if (!Objects.equals(url, ""))
            {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onSubmitClicked(ActionEvent event) {
        String category = categories[comboMenu.getSelectedIndex()];
        presenter.loadActivityFromInput(
                category, Integer.parseInt(numPeople.getValue().toString()));
    }

    public void setLink(String link) {
        url = link;
        if (!Objects.equals(link, ""))
        {
            btnLink.setText("Click for the link!");
        } else
        {
            btnLink.setText("No link with this activity");
        }
    }

    public void setActivity(String nextActivity) {
        if (nextActivity == null)
        {
            nextActivity = "Seems there are no activities with that combination. "
                    + "Try changing your filters";
        } else
        {
            output.setText(nextActivity);
        }
    }

    public void showError() {
        output.setText("Something went wrong somewhere... Please try again");
    }

    public static void main(String[] args) {
        JFrame frame = new ActivityFrame();
        frame.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        frame.setIconImage(icon);
    }
}
