package com.tianque.jms.conventer;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.redis.utils.RedisDefaultPageUtils;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.ActualPopulationMsg;
import com.tianque.jms.msg.BaseMsg;
import com.tianque.jms.msg.BusinesPopulationMsg;
import com.tianque.jms.msg.HouseMsg;
import com.tianque.jms.msg.PopulationMsg;
import com.tianque.jms.msg.PopulationSolrIndexMsg;
import com.tianque.jms.msg.RedisCacheMsg;
import com.tianque.jms.msg.SolrIndexMsg;

public class MsgConventer implements MessageConverter {

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		if (message instanceof MapMessage) {
			MapMessage msg = (MapMessage) message;
			String msgType = msg.getString("msgType");
			if ("actualPopulation".equals(msgType)) {
				return createActualPopulationMsg(msg);
			} else if ("businessPopulation".equals(msgType)) {
				return createBusinessPopulationMsg(msg);
			} else if ("houseInfo".equals(msgType)) {
				return createHouseMsg(msg);
			} else if ("population".equals(msgType)) {
				return createPopulationMsg(msg);
			} else {
				return new BaseMsg();
			}
		} else if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			if (objectMessage.getObject() instanceof RedisCacheMsg) {
				RedisCacheMsg redisCacheMsg = (RedisCacheMsg) objectMessage
						.getObject();
				return createRedisCacheMsg(redisCacheMsg);
			}
		}
		return new BaseMsg();

	}

	private Object createRedisCacheMsg(RedisCacheMsg redisCacheMsg) {
		try {
			PageInfoCacheThreadLocal pageInfoCacheThreadLocal = redisCacheMsg
					.getPageInfoCacheThreadLocal();
			switch (pageInfoCacheThreadLocal.getOperate()) {
			case INCREMENT:
				RedisDefaultPageUtils.addToList(
						pageInfoCacheThreadLocal.getModule(),
						pageInfoCacheThreadLocal.getOrgId(),
						pageInfoCacheThreadLocal.getOrgLevel(),
						pageInfoCacheThreadLocal.getBaseDomain());
				break;
			case DECREASE:
				RedisDefaultPageUtils.delFromList(
						pageInfoCacheThreadLocal.getModule(),
						pageInfoCacheThreadLocal.getOrgId(),
						pageInfoCacheThreadLocal.getOrgLevel(),
						pageInfoCacheThreadLocal.getBaseDomain());
				break;
			case UPDATE:
				RedisDefaultPageUtils.updateList(
						pageInfoCacheThreadLocal.getModule(),
						pageInfoCacheThreadLocal.getOrgId(),
						pageInfoCacheThreadLocal.getOrgLevel(),
						pageInfoCacheThreadLocal.getBaseDomain());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			RedisDefaultPageUtils.addClearListKey(
					redisCacheMsg.getObjectType(), redisCacheMsg.getOrgId());
		}
		return redisCacheMsg;
	}

	private Object createHouseMsg(MapMessage msg) throws JMSException {
		HouseMsg houseMsg = new HouseMsg();
		houseMsg.setOrgId(msg.getLong("orgId"));
		houseMsg.setHouseCode(msg.getString("houseCode"));
		houseMsg.setMode(Enum.valueOf(OperateMode.class, msg.getString("mode")));
		return houseMsg;
	}

	private Object createBusinessPopulationMsg(MapMessage msg)
			throws JMSException {
		BusinesPopulationMsg businesPopulationMsg = new BusinesPopulationMsg();
		businesPopulationMsg.setIdCardNo(msg.getString("idCardNo"));
		businesPopulationMsg.setMode(Enum.valueOf(OperateMode.class,
				msg.getString("mode")));
		businesPopulationMsg.setOrgId(msg.getLong("orgId"));
		businesPopulationMsg.setObjectType(msg.getString("objectType"));
		businesPopulationMsg.setObjectId(msg.getLong("objectId"));
		businesPopulationMsg.setActualPopulationType(msg
				.getString("actualPopulationType"));
		return businesPopulationMsg;
	}

	private Object createPopulationMsg(MapMessage msg) throws JMSException {
		PopulationMsg populationMsg = new PopulationMsg();
		populationMsg.setType(msg.getString("type"));
		populationMsg.setIdCardNo(msg.getString("idCardNo"));
		populationMsg.setOrgId(msg.getLong("orgId"));
		populationMsg.setMode(Enum.valueOf(OperateMode.class,
				msg.getString("mode")));
		populationMsg.setBaseinfoId(msg.getLong("baseinfoId"));
		populationMsg.setAddressId(msg.getLong("addressId"));
		populationMsg.setObjectId(msg.getLong("objectId"));
		populationMsg.setCreateUser(msg.getString("createUser"));
		populationMsg.setObjectType(msg.getString("objectType"));
		return populationMsg;
	}

	private Object createActualPopulationMsg(MapMessage msg)
			throws JMSException {
		ActualPopulationMsg actualPopulationMsg = new ActualPopulationMsg();
		actualPopulationMsg.setObjectId(msg.getLong("objectId"));
		actualPopulationMsg.setIdCardNo(msg.getString("idCardNo"));
		actualPopulationMsg.setMode(Enum.valueOf(OperateMode.class,
				msg.getString("mode")));
		actualPopulationMsg.setOrgId(msg.getLong("orgId"));
		actualPopulationMsg.setObjectType(msg.getString("objectType"));
		return actualPopulationMsg;
	}

	@Override
	public Message toMessage(Object msg, Session session) throws JMSException,
			MessageConversionException {
		BaseMsg baseMsg = (BaseMsg) msg;
		if (msg instanceof RedisCacheMsg) {
			ObjectMessage objectMessage = session.createObjectMessage();
			RedisCacheMsg redisCacheMsg = (RedisCacheMsg) msg;
			objectMessage.setObject(redisCacheMsg);
			return objectMessage;
		}
		MapMessage mapMessage = session.createMapMessage();
		mapMessage.setString("mode", baseMsg.getMode().toString());
		mapMessage.setString("objectType", baseMsg.getObjectType());
		mapMessage.setLong("objectId", baseMsg.getObjectId() == null ? -1L
				: baseMsg.getObjectId());
		mapMessage.setString("msgType", baseMsg.getMsgType());
		if (msg instanceof PopulationMsg) {
			PopulationMsg populationMsg = (PopulationMsg) msg;
			mapMessage.setString("type", populationMsg.getType());
			mapMessage.setLong("orgId", populationMsg.getOrgId());
			mapMessage.setString("idCardNo", populationMsg.getIdCardNo());
			mapMessage.setLong("baseinfoId", populationMsg.getBaseinfoId());
			mapMessage.setLong("addressId", populationMsg.getAddressId());
			mapMessage.setString("createUser", populationMsg.getCreateUser());
		} else {
			mapMessage.setLong("orgId", baseMsg.getOrgId() == null ? -1L
					: baseMsg.getOrgId());
			mapMessage.setString("objectList", baseMsg.getObjectList());
			if (msg instanceof BusinesPopulationMsg) {
				BusinesPopulationMsg businesPopulationMsg = (BusinesPopulationMsg) msg;
				mapMessage.setString("idCardNo",
						businesPopulationMsg.getIdCardNo());
				mapMessage.setString("actualPopulationType",
						businesPopulationMsg.getActualPopulationType());
			} else if (msg instanceof ActualPopulationMsg) {
				ActualPopulationMsg actualPopulationMsg = (ActualPopulationMsg) msg;
				mapMessage.setString("idCardNo",
						actualPopulationMsg.getIdCardNo());

			} else if (msg instanceof HouseMsg) {
				HouseMsg houseMsg = (HouseMsg) msg;
				mapMessage.setString("houseCode", houseMsg.getHouseCode());
				mapMessage.setLong("houseId", houseMsg.getHouseId());
			} else if (msg instanceof PopulationSolrIndexMsg) {
				PopulationSolrIndexMsg populationSolrIndexMsg = (PopulationSolrIndexMsg) msg;
				mapMessage.setLong("baseinfoId",
						populationSolrIndexMsg.getBaseinfoId() == null ? -1L
								: populationSolrIndexMsg.getBaseinfoId());
				mapMessage.setObject("deleteIds",
						populationSolrIndexMsg.getDeleteIds());
				mapMessage.setObject("importIdRange",
						populationSolrIndexMsg.getImportIdRange());
				mapMessage.setObject("populationMsgMap",
						populationSolrIndexMsg.getPopulationMsgMap());
			} else if (msg instanceof SolrIndexMsg) {
				SolrIndexMsg solrIndexMsg = (SolrIndexMsg) msg;
				mapMessage.setObject("msgMap", solrIndexMsg.getMsgMap());
				mapMessage.setObject("msgList", solrIndexMsg.getMsgList());
				mapMessage
						.setObject("searchType", solrIndexMsg.getSearchType());
			}
		}
		return mapMessage;
	}
}
