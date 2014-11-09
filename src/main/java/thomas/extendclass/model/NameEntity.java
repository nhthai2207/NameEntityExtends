package thomas.extendclass.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;


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

	public <T> T convertToChildClass(Class<T> clazz){
		Gson gson = new GsonBuilder().registerTypeAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();
		String json = gson.toJson(this);
		T tmp = gson.fromJson(json, clazz);		
		return tmp;
	}

}