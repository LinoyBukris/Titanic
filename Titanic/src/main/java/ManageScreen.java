import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ManageScreen extends JPanel {


    private JLabel pClassLabel;
    private JComboBox<String> pClassComboBox;

    private JLabel passengerIdLabel;
    private JLabel minLabel;
    private JTextField minTextField;
    private JLabel maxLabel;
    private JTextField maxTextField;

    private JLabel nameLabel;
    private JTextField nameTextField;

    private JLabel pSexLabel;
    private JComboBox<String> pSexComboBox;

    private JLabel sibSpLabel;
    private JTextField sibSpTextField;

    private JLabel parchLabel;
    private JTextField parchTextField;

    private JLabel ticketLabel;
    private JTextField ticketTextField;

    private JLabel fareLabel;
    private JLabel minLabel2;
    private JTextField minTextField2;
    private JLabel maxLabel2;
    private JTextField maxTextField2;

    private JLabel cabinLabel;
    private JTextField cabinTextField;

    private JLabel embarkedLabel;
    private JComboBox<String> embarkedComboBox;

    private JLabel allTextLabel;
    private JLabel totalRowsLabel;

    private List<Passenger> passengers;

    private FilterButton filterButton;
    private StatisticsButton statisticsButton;

    private JComboBox<String> mapComboBox;
    private JLabel resultsLabel;



    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE);

        if (file.exists()) {
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
            this.passengers = new ArrayList<>();



            this.pClassLabel = new JLabel("Passenger Class: ");
            this.pClassLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.pClassLabel);
            this.pClassComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
            this.pClassComboBox.setBounds(this.pClassLabel.getX() + this.pClassLabel.getWidth() - 10, this.pClassLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.pClassComboBox);

            this.passengerIdLabel = new JLabel("Passenger id: ");
            this.passengerIdLabel.setBounds(this.pClassComboBox.getX() + this.pClassComboBox.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.pClassComboBox.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.passengerIdLabel);

            this.minLabel = new JLabel("min: ");
            this.minLabel.setBounds(this.passengerIdLabel.getX() + this.passengerIdLabel.getWidth() - 30, this.passengerIdLabel.getY(), Constants.LABEL_WIDTH / 2, Constants.LABEL_HEIGHT);
            this.add(this.minLabel);
            this.minTextField = new JTextField(2);
            this.minTextField.setBounds(this.minLabel.getX() + this.minLabel.getWidth() - 30, this.minLabel.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.minTextField);

            this.maxLabel = new JLabel("max: ");
            this.maxLabel.setBounds(this.minTextField.getX() + this.minTextField.getWidth(), this.minTextField.getY(), Constants.LABEL_WIDTH / 2, Constants.LABEL_HEIGHT);
            this.add(this.maxLabel);
            this.maxTextField = new JTextField(2);
            this.maxTextField.setBounds(this.maxLabel.getX() + this.maxLabel.getWidth() - 30, this.maxLabel.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.maxTextField);

            this.nameLabel = new JLabel("Passenger name: ");
            this.nameLabel.setBounds(this.maxTextField.getX() + this.maxTextField.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.maxTextField.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.nameLabel);
            this.nameTextField = new JTextField(6);
            this.nameTextField.setBounds(this.nameLabel.getX() + this.nameLabel.getWidth() - 10, this.nameLabel.getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
            this.add(this.nameTextField);

            this.pSexLabel = new JLabel("Passenger Sex: ");
            this.pSexLabel.setBounds(this.nameTextField.getX() + this.nameTextField.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.nameTextField.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.pSexLabel);
            this.pSexComboBox = new JComboBox<>(Constants.PASSENGER_SEX_OPTIONS);
            this.pSexComboBox.setBounds(this.pSexLabel.getX() + this.pSexLabel.getWidth() - 10, this.pSexLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.pSexComboBox);

            this.sibSpLabel = new JLabel("Passenger SibSp: ");
            this.sibSpLabel.setBounds(this.pSexComboBox.getX() + this.pSexComboBox.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.pSexComboBox.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.sibSpLabel);
            this.sibSpTextField = new JTextField(2);
            this.sibSpTextField.setBounds(this.sibSpLabel.getX() + this.sibSpLabel.getWidth() - 5, this.sibSpLabel.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.sibSpTextField);

            this.parchLabel = new JLabel("Passenger Parch: ");
            this.parchLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, this.sibSpTextField.getY() + this.sibSpTextField.getWidth() + 2 * Constants.MARGIN_FROM_TOP, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.parchLabel);
            this.parchTextField = new JTextField(2);
            this.parchTextField.setBounds(this.parchLabel.getX() + this.parchLabel.getWidth() - 5, this.parchLabel.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.parchTextField);

            this.ticketLabel = new JLabel("Passenger ticket: ");
            this.ticketLabel.setBounds(this.parchTextField.getX() + this.parchTextField.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.parchTextField.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.ticketLabel);
            this.ticketTextField = new JTextField(2);
            this.ticketTextField.setBounds(this.ticketLabel.getX() + this.ticketLabel.getWidth() - 5, this.ticketLabel.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.ticketTextField);

            this.fareLabel = new JLabel("Passenger fare: ");
            this.fareLabel.setBounds(this.ticketTextField.getX() + this.ticketTextField.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.ticketTextField.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.fareLabel);

            this.minLabel2 = new JLabel("min: ");
            this.minLabel2.setBounds(this.fareLabel.getX() + this.fareLabel.getWidth() - 20, this.fareLabel.getY(), Constants.LABEL_WIDTH / 2, Constants.LABEL_HEIGHT);
            this.add(this.minLabel2);
            this.minTextField2 = new JTextField(2);
            this.minTextField2.setBounds(this.minLabel2.getX() + this.minLabel2.getWidth() - 30, this.minLabel2.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.minTextField2);

            this.maxLabel2 = new JLabel("max: ");
            this.maxLabel2.setBounds(this.minTextField2.getX() + this.minTextField2.getWidth(), this.minTextField2.getY(), Constants.LABEL_WIDTH / 2, Constants.LABEL_HEIGHT);
            this.add(this.maxLabel2);
            this.maxTextField2 = new JTextField(2);
            this.maxTextField2.setBounds(this.maxLabel2.getX() + this.maxLabel2.getWidth() - 30, this.maxLabel2.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.maxTextField2);

            this.cabinLabel = new JLabel("Passenger Cabin: ");
            this.cabinLabel.setBounds(this.maxTextField2.getX() + this.maxTextField2.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.maxTextField2.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.cabinLabel);
            this.cabinTextField = new JTextField(2);
            this.cabinTextField.setBounds(this.cabinLabel.getX() + this.cabinLabel.getWidth() - 5, this.cabinLabel.getY(), Constants.LABEL_WIDTH / 6, Constants.LABEL_HEIGHT);
            this.add(this.cabinTextField);

            this.embarkedLabel = new JLabel("Passenger Embarked: ");
            this.embarkedLabel.setBounds(this.cabinTextField.getX() + this.cabinTextField.getWidth() + 3 * Constants.MARGIN_FROM_LEFT, this.cabinTextField.getY(), 5 * Constants.LABEL_WIDTH / 4, Constants.LABEL_HEIGHT);
            this.add(this.embarkedLabel);
            this.embarkedComboBox = new JComboBox<>(Constants.PASSENGER_EMBARKED_OPTIONS);
            this.embarkedComboBox.setBounds(embarkedLabel.getX() + embarkedLabel.getWidth(), embarkedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.embarkedComboBox);

            this.filterButton = new FilterButton(this);

            this.statisticsButton = new StatisticsButton(this);

            this.mapComboBox = new JComboBox<>(Constants.CATEGORIES);
            this.mapComboBox.setBounds(this.statisticsButton.getX(), this.embarkedComboBox.getY(), 2*Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.mapComboBox);
            mapComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCategory = (String) mapComboBox.getSelectedItem();
                    Map<String, Float> results = analyzeData(selectedCategory);
                    displayResults(results);
                }
            });
            this.resultsLabel = new JLabel();
            this.resultsLabel.setVerticalAlignment(JLabel.TOP);
            this.resultsLabel.setBounds(this.mapComboBox.getX()  , this.parchLabel.getY() + Constants.LABEL_HEIGHT + Constants.MARGIN_FROM_TOP,
                    width - Constants.LABEL_WIDTH - 2 * Constants.MARGIN_FROM_LEFT, Constants.WINDOW_HEIGHT - Constants.LABEL_HEIGHT - 2 * Constants.MARGIN_FROM_TOP);
            this.resultsLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.add(this.resultsLabel);




            this.allTextLabel = new JLabel();
            this.totalRowsLabel = new JLabel();
            totalRowsLabel.setBounds(this.parchLabel.getX() + Constants.MARGIN_FROM_LEFT, this.parchLabel.getY() + Constants.LABEL_HEIGHT + Constants.MARGIN_FROM_TOP, 12 * Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(totalRowsLabel);
            JScrollPane scrollPane = new JScrollPane(allTextLabel);
            scrollPane.setBounds(this.parchLabel.getX() + Constants.MARGIN_FROM_LEFT, totalRowsLabel.getY() + Constants.LABEL_HEIGHT + Constants.MARGIN_FROM_TOP,mapComboBox.getX() -(this.parchLabel.getX() + Constants.MARGIN_FROM_LEFT  * Constants.MARGIN_FROM_LEFT), Constants.WINDOW_HEIGHT - Constants.LABEL_HEIGHT - 2 * Constants.MARGIN_FROM_TOP);
            this.add(scrollPane);




            try (BufferedReader br = new BufferedReader(new FileReader(Constants.PATH_TO_DATA_FILE))) {
                String line;

                int i = -1;

                while ((line = br.readLine()) != null) {
                    i++;
                    if (i >= 1) {
                        List<String> passengersParts = List.of(line.split(","));


                        String passengerId = passengersParts.get(0);
                        int survived = Integer.parseInt(passengersParts.get(1));
                        int pclass = Integer.parseInt(passengersParts.get(2));
                        String name = passengersParts.get(3) + passengersParts.get(4);
                        String sex = passengersParts.get(5);
                        String age = passengersParts.get(6);
                        int sibSp = Integer.parseInt(passengersParts.get(7));
                        int parch = Integer.parseInt(passengersParts.get(8));
                        String ticket = passengersParts.get(9);
                        double fare = Double.parseDouble(passengersParts.get(10));
                        String cabin = passengersParts.get(11);
                        String embarked = "";
                        if (passengersParts.size() > 12) {
                            embarked = passengersParts.get(12);
                        }

                        this.passengers.add(new Passenger(passengerId, survived, pclass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked));



                    }

                }
                setListAsTextOnScreen(passengers);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private Map<String, Float> analyzeData(String category) {
        Map<String, Float> resultMap = new HashMap<>();

        switch (category) {
            case "Survived":
                resultMap = calculatePercentageBySurvival();
                break;
            case "Pclass":
                resultMap = calculatePercentageByPclass();
                break;
            case "Sex":
                resultMap = calculatePercentageBySex();
                break;
            case "Embarked":
                resultMap = calculatePercentageByEmbarked();
                break;
        }

        return resultMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Float>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private Map<String, Float> calculatePercentageBySurvival() {
        Map<String, Float> map = new HashMap<>();
        long totalSurvived = passengers.stream().filter(p -> p.getSurvived() == Passenger.SURVIVED).count();
        long totalNotSurvived = passengers.stream().filter(p -> p.getSurvived() == 0).count();
        map.put("Survived", (float) totalSurvived / (totalSurvived + totalNotSurvived) * 100);
        return map;
    }

    private Map<String, Float> calculatePercentageByPclass() {
        final Map<String, Float> map = new LinkedHashMap<>();

        for (int pclass = 1; pclass <= 3; pclass++) {
            int pclass1 = pclass;
            List<Passenger> passengersInClass = passengers.stream()
                    .filter(p -> p.getPclass() == pclass1)
                    .collect(Collectors.toList());

            if (passengersInClass.isEmpty()) {
                map.put("Class " + pclass, 0f);
            } else {
                long survivedCount = passengersInClass.stream()
                        .filter(p -> p.getSurvived() == Passenger.SURVIVED)
                        .count();

                float percentage = (float) survivedCount / passengersInClass.size() * 100;
                map.put("Class " + pclass, percentage);
            }
        }
        return map;
    }



    private Map<String, Float> calculatePercentageBySex() {
        Map<String, Float> map = new HashMap<>();
        List<String> sexes = Arrays.asList("male", "female");
        for (String sex : sexes) {
            List<Passenger> passengersBySex = passengers.stream().filter(p -> p.getSex().equals(sex)).collect(Collectors.toList());
            long survivedCount = passengersBySex.stream().filter(p -> p.getSurvived() == Passenger.SURVIVED).count();
            map.put(sex.substring(0, 1).toUpperCase() + sex.substring(1), (float) survivedCount / passengersBySex.size() * 100);
        }
        return map;
    }

    private Map<String, Float> calculatePercentageByEmbarked() {
        Map<String, Float> map = new HashMap<>();
        Set<String> ports = passengers.stream().map(Passenger::getEmbarked).filter(Objects::nonNull).collect(Collectors.toSet());

        for (String port : ports) {
            List<Passenger> passengersFromPort = passengers.stream().filter(p -> port.equals(p.getEmbarked())).collect(Collectors.toList());
            long survivedCount = passengersFromPort.stream().filter(p -> p.getSurvived() == Passenger.SURVIVED).count();
            map.put(port, (float) survivedCount / passengersFromPort.size() * 100);
        }

        return map;
    }

    private void displayResults(Map<String, Float> results) {
        StringBuilder sb = new StringBuilder("<html>");
        for (Map.Entry<String, Float> entry : results.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(String.format("%.2f", entry.getValue())).append("%<br>"); // <br> לשורות חדשות
        }
        sb.append("</html>");

        resultsLabel.setText(sb.toString());
    }



    public void setListAsTextOnScreen(List<Passenger> passengers){
        StringBuilder allText = new StringBuilder("<html>");
        StringBuilder totalRowsText = new StringBuilder("<html>");
        int cntSurvived=0;

        for (int i=0; i<passengers.size(); i++){
            allText.append(passengers.get(i).toString()).append("<br>");

            if (passengers.get(i).getSurvived() == Passenger.SURVIVED)
                cntSurvived++;
        }
        totalRowsText.append("Total Rows: ").append(passengers.size()).append("(").append(cntSurvived).append(" survived, ").append(passengers.size() -cntSurvived).append(" not survived)").append("</html>");
        allText.append("</html>");



        totalRowsLabel.setText(totalRowsText.toString());
        allTextLabel.setText(allText.toString());

    }



    public JLabel getpClassLabel() {
        return pClassLabel;
    }

    public JComboBox<String> getpClassComboBox() {
        return pClassComboBox;
    }

    public JLabel getPassengerIdLabel() {
        return passengerIdLabel;
    }

    public JLabel getMinLabel() {
        return minLabel;
    }

    public JTextField getMinTextField() {
        return minTextField;
    }

    public JLabel getMaxLabel() {
        return maxLabel;
    }

    public JTextField getMaxTextField() {
        return maxTextField;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JLabel getpSexLabel() {
        return pSexLabel;
    }

    public JComboBox<String> getpSexComboBox() {
        return pSexComboBox;
    }

    public JLabel getSibSpLabel() {
        return sibSpLabel;
    }

    public JTextField getSibSpTextField() {
        return sibSpTextField;
    }

    public JLabel getParchLabel() {
        return parchLabel;
    }

    public JTextField getParchTextField() {
        return parchTextField;
    }

    public JLabel getTicketLabel() {
        return ticketLabel;
    }

    public JTextField getTicketTextField() {
        return ticketTextField;
    }

    public JLabel getFareLabel() {
        return fareLabel;
    }

    public JLabel getMinLabel2() {
        return minLabel2;
    }

    public JTextField getMinTextField2() {
        return minTextField2;
    }

    public JLabel getMaxLabel2() {
        return maxLabel2;
    }

    public JTextField getMaxTextField2() {
        return maxTextField2;
    }

    public JLabel getCabinLabel() {
        return cabinLabel;
    }

    public JTextField getCabinTextField() {
        return cabinTextField;
    }

    public JLabel getEmbarkedLabel() {
        return embarkedLabel;
    }

    public JComboBox<String> getEmbarkedComboBox() {
        return embarkedComboBox;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public JLabel getAllTextLabel() {
        return allTextLabel;
    }

    public JLabel getTotalRowsLabel() {
        return totalRowsLabel;
    }

    public FilterButton getFilterButton() {
        return filterButton;
    }

    public StatisticsButton getStatisticsButton() {
        return statisticsButton;
    }
}
