package thomas.extendclass.core;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import thomas.extendclass.model.ArgEntity;
import thomas.extendclass.model.CollectionName;
import thomas.extendclass.model.FuncEntity;
import thomas.extendclass.model.NameEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class App {
	private DB mongodb;

	public App() {
		try {
			Mongo mongo = new Mongo("127.0.0.1", 27017);
			mongodb = mongo.getDB("thomas");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		App mc = new App();
		List<FuncEntity> funcList = mc.getList(FuncEntity.class);
		List<ArgEntity> argList = mc.getList(ArgEntity.class);
		List<NameEntity> nameEntityList = new ArrayList<NameEntity>();
		nameEntityList.addAll(funcList);
		nameEntityList.addAll(argList);
		NameDepot depot = new NameDepot(nameEntityList);
	}

	private <T> List<T> getList(Class<T> clazz) {
		List<T> funcList = new ArrayList<T>();
		CollectionName annotation = clazz.getAnnotation(CollectionName.class);
		try {
			DBCollection collection = mongodb.getCollection(annotation.name());
			DBCursor cursor = collection.find();
			Gson gson = new GsonBuilder().registerTypeAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();				
				T fromJson = gson.fromJson(obj.toString(), clazz);
				funcList.add(fromJson);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcList;
	}
	/*
	 * private static void useSpring() { ApplicationContext ctx = new
	 * AnnotationConfigApplicationContext(SpringMongoConfig.class);
	 * MongoOperations mongoOperation = (MongoOperations)
	 * ctx.getBean("mongoTemplate"); List<FuncEntity> funcList =
	 * mongoOperation.findAll(FuncEntity.class); List<ArgEntity> argList =
	 * mongoOperation.findAll(ArgEntity.class); List<NameEntity> nameEntityList
	 * = new ArrayList<NameEntity>(); nameEntityList.addAll(funcList);
	 * nameEntityList.addAll(argList); NameDepot depot = new
	 * NameDepot(nameEntityList);
	 * 
	 * }
	 */
}

class ObjectIdTypeAdapter extends TypeAdapter<ObjectId> {
	@Override
	public void write(final JsonWriter out, final ObjectId value) throws IOException {
		out.beginObject().name("$oid").value(value.toString()).endObject();
	}

	@Override
	public ObjectId read(final JsonReader in) throws IOException {
		in.beginObject();
		String key = in.nextName();
		String value = in.nextString();
		// assert "$oid".equals(in.nextName());
		// String objectId = in.nextString();
		in.endObject();
		if ("$oid".equals(key)) {
			return new ObjectId(value);
		}
		return null;
	}
}