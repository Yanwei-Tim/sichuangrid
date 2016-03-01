package com.tianque.shard.util;

import java.util.ArrayList;
import java.util.List;

public class ShardTables {
	
	public static final String SHARD_HOUSEHOLDSTAFFS = "householdstaffs";
	
	public static final String SHARD_NURTURESWOMEN = "nurtureswomen";
	
	public static final String SHARD_ELDERLYPEOPLE = "elderlypeople";
	
	public static final String SHARD_HOUSEINFO = "houseinfo";
	
	public static final List<String> shardTablesList = new ArrayList<String>();
	
	static{
		shardTablesList.add(SHARD_HOUSEHOLDSTAFFS);
		shardTablesList.add(SHARD_NURTURESWOMEN);
		shardTablesList.add(SHARD_ELDERLYPEOPLE);
		shardTablesList.add(SHARD_HOUSEINFO);
	}
	
	public static boolean isShardTables(String tableName){
		if(tableName == null || "".equals(tableName)){
			return false;
		}
		return shardTablesList.contains(tableName.toLowerCase());
	}
}
