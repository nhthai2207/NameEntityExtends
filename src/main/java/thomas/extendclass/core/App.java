package thomas.extendclass.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import thomas.extendclass.config.SpringMongoConfig;
import thomas.extendclass.model.ArgEntity;
import thomas.extendclass.model.FuncEntity;
import thomas.extendclass.model.NameEntity;

public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		List<FuncEntity> funcList = mongoOperation.findAll(FuncEntity.class);
		List<ArgEntity> argList = mongoOperation.findAll(ArgEntity.class);
		List<NameEntity> nameEntityList = new ArrayList<NameEntity>(); 
		nameEntityList.addAll(funcList);
		nameEntityList.addAll(argList);
		NameDepot depot = new NameDepot(nameEntityList);
		
	}

}