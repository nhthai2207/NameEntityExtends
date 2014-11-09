package thomas.extendclass.model;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ObjectIdTypeAdapter extends TypeAdapter<ObjectId> {
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