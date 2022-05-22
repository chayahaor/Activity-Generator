import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.Objects;

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

        JPanel horizontalLayout = new JPanel();
        horizontalLayout.setLayout(new BoxLayout(horizontalLayout, BoxLayout.X_AXIS));
        add(horizontalLayout);

        JPanel borderLayout = new JPanel();
        borderLayout.setLayout(new BorderLayout());
        add(borderLayout);

        comboMenu = new JComboBox<>(categories);
        horizontalLayout.add(comboMenu);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 5, 1);

        numPeople = new JSpinner(spinnerModel);
        horizontalLayout.add(numPeople);

        borderLayout.add(horizontalLayout, BorderLayout.PAGE_START);

        submitButton = new JButton();
        submitButton.setText("Click for an activity");
        submitButton.addActionListener(this::onSubmitClicked);
        borderLayout.add(submitButton, BorderLayout.BEFORE_LINE_BEGINS);


        btnLink = new JButton();
        btnLink.addActionListener(this::onClickLink);
        btnLink.setText("Get started to see links here");
        borderLayout.add(btnLink, BorderLayout.AFTER_LINE_ENDS);

        output = new JLabel();
        output.setText("Let's get started!");
        borderLayout.add(output, BorderLayout.AFTER_LAST_LINE);



        GetActivityServiceFactory factory = new GetActivityServiceFactory();
        presenter = new ActivityPresenter(this, factory.getInstance());

    }


    private void onClickLink(ActionEvent event) {
        try
        {
            if (!"".equals(url))
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
        if (nextActivity.contains("null"))
        {
            nextActivity = "Seems there are no activities with that combination. "
                    + "Try changing your filters";
        }
        output.setText(nextActivity);

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
