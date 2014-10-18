package thomas.extendclass.model;

import org.bson.types.ObjectId;


public class NameEntity {
	
	private ObjectId _id;
	private String name;	

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	

}