package thomas.extendclass.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "argument")
public class ArgEntity extends NameEntity {
	private String optional;
	private String description;
	private String function_id;
	private String optional_to_argument_id;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOptional_to_argument_id() {
		return optional_to_argument_id;
	}

	public void setOptional_to_argument_id(String optional_to_argument_id) {
		this.optional_to_argument_id = optional_to_argument_id;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getFunction_id() {
		return function_id;
	}

	public void setFunction_id(String function_id) {
		this.function_id = function_id;
	}
}