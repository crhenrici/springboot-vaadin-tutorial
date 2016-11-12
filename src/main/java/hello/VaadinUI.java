package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private final CustomerRepository repo;
	
	private final CustomerEditor editor;
	
	final Grid grid;
	
	final TextField filter;
	
	private final Button addNewBtn;
	
	@Autowired
	public VaadinUI(CustomerRepository repo, CustomerEditor editor) {
		this.repo = repo;
		this.editor = editor; 
		this.grid = new Grid();
		this.filter = new TextField();
		this.addNewBtn = new Button("New Customer", FontAwesome.PLUS);
		}

	@Override
	protected void init(VaadinRequest request) {
		//Layout
				HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
				VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
				setContent(mainLayout);
				
				//configuration layouts
				actions.setSpacing(true);
				mainLayout.setMargin(true);
				mainLayout.setSpacing(true);
				
				grid.setHeight(300, Unit.PIXELS);
				grid.setColumns("id", "firstName", "lastName", "ueberName", "stadt");
				
				filter.setInputPrompt("Filter by Last Name");
				
				//replace listing with filtered content when user changes filter
				filter.addTextChangeListener(e -> listCustomers(e.getText()));
				
				grid.addSelectionListener(e ->  {
					if (e.getSelected().isEmpty()) {
						editor.setVisible(false);
					}
					else {
						editor.editCustomer((Customer)grid.getSelectedRow());
					}
				});
				
				//add new user
				addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "", "", "")));
				
				editor.setChangeHandler(() -> {
					editor.setVisible(false);
					listCustomers(filter.getValue());
				});
				
				listCustomers(null);
	}
				void listCustomers(String text) {
					if (StringUtils.isEmpty(text)) {
						grid.setContainerDataSource(
						new BeanItemContainer(Customer.class, repo.findAll()));
					}
					else {
						grid.setContainerDataSource(new BeanItemContainer(Customer.class, repo.findByLastNameStartsWithIgnoreCase(text)));
					}
			}		
}
