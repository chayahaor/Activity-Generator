import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActivityFrame extends JFrame {

    private JComboBox<String> comboMenu;
    private JButton submitButton;
    private JLabel output;
    private JSpinner numPeople;
    private final String[] categories = {"education", "recreational", "social",
            "diy", "charity", "cooking", "relaxation", "music", "busywork"};

    ActivityPresenter presenter;

    public ActivityFrame() {
        setTitle("Activity Generator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        comboMenu = new JComboBox<>(categories);
        add(comboMenu);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 5, 1);
        numPeople = new JSpinner(spinnerModel);
        add(numPeople);

        submitButton = new JButton();
        submitButton.setText("Click for an activity");
        submitButton.addActionListener(this::onSubmitClicked);
        add(submitButton);


        output = new JLabel();
        output.setText("Let's get started!");
        add(output);

        GetActivityServiceFactory factory = new GetActivityServiceFactory();
        presenter = new ActivityPresenter(this, factory.getInstance());

    }

    public void onSubmitClicked(ActionEvent event) {
        String category = categories[comboMenu.getSelectedIndex()];
        presenter.loadActivityFromInput(category, Integer.parseInt(numPeople.getValue().toString()));
    }

    public static void main(String[] args) {
        JFrame frame = new ActivityFrame();
        frame.setVisible(true);
    }

    public void setActivity(String nextActivity) {
        output.setText(nextActivity);
    }

    public void showError() {
        output.setText("Something went wrong somewhere... Please try again");
    }
}
