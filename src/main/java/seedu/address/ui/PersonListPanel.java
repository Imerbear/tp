package seedu.address.ui;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanelUpdated.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private SelectedPersonCard selected = new SelectedPersonCard();

    @FXML
    private ListView<Person> personListView;

    @FXML
    private StackPane selectedPersonPanelPlaceholder;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        setSelectedPersonPanel(personList);
        selectedPersonPanelPlaceholder.getChildren().add(selected.getRoot());
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Person selectedPerson = personListView.getSelectionModel().getSelectedItem();
                selected.setPersonDetails(selectedPerson);
            }
        });
    }


    public void setSelectedPersonPanel(ObservableList<Person> personList) {
        if (!personList.isEmpty()) {
            selected.setPersonDetails(personList.get(0));
        } else {
            selected.setEmptyPersonDetails();
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
