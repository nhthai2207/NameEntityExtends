package thomas.extendclass.model.category;

import thomas.extendclass.model.CollectionName;
import thomas.extendclass.model.NameEntity;

@CollectionName(name = "import_as_name")
public class ImportAsName extends NameEntity{
	private String alias;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	

}