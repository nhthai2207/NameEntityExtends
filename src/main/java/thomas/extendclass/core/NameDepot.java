package thomas.extendclass.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.bson.types.ObjectId;

import thomas.extendclass.model.ArgEntity;
import thomas.extendclass.model.CollectionName;
import thomas.extendclass.model.FuncEntity;
import thomas.extendclass.model.NameEntity;
import thomas.extendclass.model.ObjectIdTypeAdapter;
import thomas.extendclass.model.category.ClassDef;
import thomas.extendclass.model.category.DottedAsName;
import thomas.extendclass.model.category.DottedName;
import thomas.extendclass.model.category.ImportAsName;
import thomas.extendclass.utils.CommonUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.srlt.pygen.symbols.NameTerminalSymbol;

enum Categories {
	tfpdef(ArgEntity.class), funcdef(FuncEntity.class), classdef(ClassDef.class), dotted_name(DottedName.class), import_as_name(ImportAsName.class), dotted_as_name(DottedName.class);
	private Class<?> clazz;

	Categories(Class<?> clazz) {
		this.setClazz(clazz);
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
};

public class NameDepot {

	private List<NameEntity> nameEntityList;
	private Set<NameTerminalSymbol> symbolSet;
	private Map<String, List<? extends NameEntity>> categories;

	public NameDepot(List<NameEntity> nameEntityList) {
		this.nameEntityList = nameEntityList;
		this.symbolSet = new TreeSet<NameTerminalSymbol>();
		for (NameEntity nameEntity : this.nameEntityList) {
			this.symbolSet.add(new NameTerminalSymbol(nameEntity.getName()));
		}
		this.buildCategories();
	}

	public void output() {
		System.out.println("Size of entity: " + this.nameEntityList.size());
		System.out.println("Size of symbol: " + this.symbolSet.size());
		for (String key : this.categories.keySet()) {
			System.out.println("Size of categories: " + key + " = " + this.categories.get(key).size());
		}
	}

	public void saveCategoriesToMongo(DB mongodb) {
		Gson gson = new GsonBuilder().create();// .registerTypeAdapter(ObjectId.class,
												// new
												// ObjectIdTypeAdapter()).create();
		for (String key : this.categories.keySet()) {
			List<? extends NameEntity> list = this.categories.get(key);
			for (NameEntity nameEntity : list) {
				if (!(nameEntity instanceof FuncEntity) && !(nameEntity instanceof ArgEntity)) {
					Class<? extends NameEntity> class1 = nameEntity.getClass();
					CollectionName annotation = class1.getAnnotation(CollectionName.class);
					try {
						DBCollection collection = mongodb.getCollection(annotation.name());
						String json = gson.toJson(nameEntity);
						BasicDBObject tmp = gson.fromJson(json, BasicDBObject.class);
						collection.save(tmp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void buildCategories() {
		this.categories = new HashMap<String, List<? extends NameEntity>>();
		this.categories.put(Categories.tfpdef.name(), new ArrayList<ArgEntity>());
		this.categories.put(Categories.funcdef.name(), new ArrayList<FuncEntity>());
		this.categories.put(Categories.classdef.name(), new ArrayList<ClassDef>());
		this.categories.put(Categories.dotted_name.name(), new ArrayList<DottedName>());
		this.categories.put(Categories.import_as_name.name(), new ArrayList<ImportAsName>());
		this.categories.put(Categories.dotted_as_name.name(), new ArrayList<DottedAsName>());

		for (NameEntity nameEntity : nameEntityList) {
			if (nameEntity instanceof ArgEntity) {
				ArgEntity arg = (ArgEntity) nameEntity;
				((List<ArgEntity>) this.categories.get(Categories.tfpdef.name())).add(arg);
			}
			if (nameEntity instanceof FuncEntity) {
				FuncEntity func = (FuncEntity) nameEntity;
				((List<FuncEntity>) this.categories.get(Categories.funcdef.name())).add(func);
				FuncEntity tmp = (FuncEntity) nameEntity;
				if (!CommonUtils.isEmptyString(tmp.getClazz())) {
					((List<ClassDef>) this.categories.get(Categories.classdef.name())).add(func.convertToChildClass(ClassDef.class));
				}
				if (!CommonUtils.isEmptyString(tmp.getModule())) {
					((List<DottedName>) this.categories.get(Categories.dotted_name.name())).add(func.convertToChildClass(DottedName.class));
				}
				if (!CommonUtils.isEmptyString(tmp.getModule())) {
					ImportAsName importAsName = func.convertToChildClass(ImportAsName.class);
					importAsName.setAlias("alias");
					((List<ImportAsName>) this.categories.get(Categories.import_as_name.name())).add(importAsName);
				}
			}
		}
	}

	public List<NameEntity> getNameEntityList() {
		return nameEntityList;
	}

	public void setNameEntityList(List<NameEntity> nameEntityList) {
		this.nameEntityList = nameEntityList;
	}

	public Set<NameTerminalSymbol> getSymbolSet() {
		return symbolSet;
	}

	public void setSymbolSet(Set<NameTerminalSymbol> symbolSet) {
		this.symbolSet = symbolSet;
	}
}