package client.gui;
import java.io.IOException;

import client.Client;
import client.connection.StreamManager;
import client.packet.database.StatementPacket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ProjectController {
	
	@FXML//C1127 add
	public void changeScreenButtonPushed(ActionEvent event) throws IOException
	{
//        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddTenant.fxml"));
//        Scene tableViewScene = new Scene(tableViewParent);
//        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    }
	
	//c1127
    @FXML
    private Text lateFeeParameterLabel;

    @FXML
    private TextField depositAmountInput;

    @FXML
    private Text depositAmountLabel;

    @FXML
    private TextField lateFeeParameterInput;

    @FXML
    private TextField maxOccInput;

    @FXML
    private Text tenLastNameLabel;

    @FXML
    private Text leaseDatesLabel;

    @FXML
    private Text propertyIDLabel;

    @FXML
    private Button submitTenantButton;

    @FXML
    private TextField lastNameInput;

    @FXML
    private GridPane addTenantMenu;

    @FXML
    private GridPane ProjectMenu;

    @FXML
    private Text leaseTermsLabel;

    @FXML
    private Text tenFirstNameLabel;

    @FXML
    private GridPane addTenantMenu1;

    @FXML
    private Text tenPhoneLabel;

    @FXML
    private TextField tenantIDInput;

    @FXML
    private Text addRentalLabel;

    @FXML
    private TextField idInput;

    @FXML
    private Text tenantIDLabel;

    @FXML
    private TextField leaseTermsInput;

    @FXML
    private Button submitPropertyButtonPressed1;

    @FXML
    private TextField propertyIDInput;

    @FXML
    private Text tenAddressLabel;

    @FXML
    private TextField phoneInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private Button submitPropertyButtonPressed;

    @FXML
    private TextField firstNameInput;

    @FXML
    private Text addTermsLabel;

    @FXML
    private Text maxOccLabel;

    @FXML
    private Text idLabel;

    @FXML
    private GridPane addTenantMenu11;

    @FXML
    private TextField leaseDatesInput;

    @FXML
    private TextField addressInput;

    @FXML
    private Text descriptionLabel;

    @FXML
    private TextField leaseCostInput;
    
    @FXML
    void submitTenantButtonPressed(ActionEvent event) {
		System.out.println("Submit Tenant Button Query");
		String firstName = (firstNameInput.getText());
		String lastName = (lastNameInput.getText());
		int phone = Integer.parseInt(phoneInput.getText());
		String address = (addressInput.getText());
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(phone);
		System.out.println(address);
		
		String stmt = String.format("INSERT INTO Tenants (tenantID, firstName, lastName, phoneNumber, billingAddress) VALUES (%d, \'%s\', \'%s\', \'%d\', \'%s\');", 
				(int) (Math.random() * Integer.MAX_VALUE), firstName, lastName, phone, address);
		
		StatementPacket packet = new StatementPacket(stmt, result -> {
			System.out.println(result.success ? "Success" : "Failed");
			// Display some sort of confirmation message here?
		});
		
		StreamManager.sendPacket(packet, Client.serverSocket);
		
		firstNameInput.clear();
		lastNameInput.clear();
		addressInput.clear();
		phoneInput.clear();
    }

    @FXML
    void submitNewPropertyButtonPressed(ActionEvent event) {
    	
    }

    @FXML
    void submitNewContractButtonPressed(ActionEvent event) {

    }

}
