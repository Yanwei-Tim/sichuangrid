package com.tianque.mongodb.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.tianque.core.util.GridProperties;

public class DatastoreFactoryBean extends AbstractFactoryBean {
	private String url;
	private String dbname;
	private String[] packageNames;
	private String[] classesNames;
	private boolean ignoreInvalidClasses = false;
	private Properties options;
	private boolean slaveOk = false;
	private boolean ensureIndexes = false;

	@SuppressWarnings("deprecation")
	private MongoClientOptions createMongoClientOptions() {

		MongoClientOptions.Builder builder = MongoClientOptions.builder();
		if (options != null) {
			if (options.containsKey("connectTimeout")) {
				builder.connectTimeout(Integer.valueOf(options
						.getProperty("connectTimeout")));
			}
			if (options.containsKey("maxWaitTime")) {
				builder.maxWaitTime(Integer.valueOf(options
						.getProperty("maxWaitTime")));
			}
			if (options.containsKey("autoConnectRetry")) {
				builder.autoConnectRetry(Boolean.valueOf(options
						.getProperty("autoConnectRetry")));
			}
			if (options.containsKey("connectionsPerHost")) {
				builder.connectionsPerHost(Integer.valueOf(options
						.getProperty("connectionsPerHost")));
			}
			if (options.containsKey("socketKeepAlive")) {
				builder.socketKeepAlive(Boolean.valueOf(options
						.getProperty("socketKeepAlive")));
			}
			if (options.containsKey("socketTimeout")) {
				builder.socketTimeout(Integer.valueOf(options
						.getProperty("socketTimeout")));
			}
			if (options
					.containsKey("threadsAllowedToBlockForConnectionMultiplier")) {
				builder.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(options
						.getProperty("threadsAllowedToBlockForConnectionMultiplier")));
			}
		}
		return builder.build();
	}

	private Morphia createMorphia() throws ClassNotFoundException {
		Morphia morphia = new Morphia();
		if (packageNames != null) {
			for (String packageName : packageNames) {
				morphia.mapPackage(packageName, ignoreInvalidClasses);
			}
		}
		if (classesNames != null) {
			for (String classesName : classesNames) {
				morphia.map(Class.forName(classesName));
			}
		}
		return morphia;
	}

	private List<ServerAddress> createAddress() throws Exception {
		if (url == null || url.trim().isEmpty()) {
			throw new RuntimeException("url is not null");
		}
		String[] urls = url.split(";");
		List<ServerAddress> addresses = new ArrayList<ServerAddress>(
				urls.length);
		for (String u : urls) {
			String[] hostAndPort = u.split(":");
			addresses.add(new ServerAddress(hostAndPort[0], Integer
					.valueOf(hostAndPort[1])));
		}
		return addresses;
	}

	@Override
	protected Object createInstance() throws Exception {
		if (GridProperties.ISMONGODBENABLED) {
			MongoClient mongoClient = new MongoClient(createAddress(),
					createMongoClientOptions());
			if (slaveOk) {
				mongoClient.setReadPreference(ReadPreference
						.secondaryPreferred());
			}
			Datastore datastore = createMorphia().createDatastore(mongoClient,
					dbname);
			if (ensureIndexes) {
				datastore.ensureIndexes(true);
			}
			return datastore;
		} else {
			return null;
		}
	}

	@Override
	public Class getObjectType() {
		return Datastore.class;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSlaveOk(boolean slaveOk) {
		this.slaveOk = slaveOk;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public void setPackageNames(String[] packageNames) {
		this.packageNames = packageNames;
	}

	public void setClassesNames(String[] classesNames) {
		this.classesNames = classesNames;
	}

	public void setIgnoreInvalidClasses(boolean ignoreInvalidClasses) {
		this.ignoreInvalidClasses = ignoreInvalidClasses;
	}

	public void setOptions(Properties options) {
		this.options = options;
	}

	public void setEnsureIndexes(boolean ensureIndexes) {
		this.ensureIndexes = ensureIndexes;
	}

}
