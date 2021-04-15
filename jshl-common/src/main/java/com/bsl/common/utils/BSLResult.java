package com.bsl.common.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 宝顺来自定义响应结构
 */
public class BSLResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    
    //类名
  	private String className;
  	//方法名
  	private String methodName;
  	private long total;
	private List<?> rows;

    public static BSLResult build(Integer status, String msg, Object data) {
        return new BSLResult(status, msg, data);
    }

    public static BSLResult ok(Object data) {
        return new BSLResult(data);
    }
    
    public static BSLResult ok(Object data, String className, String methodName) {
        return new BSLResult(200, "OK", data, className, methodName);
    }
    
    public static BSLResult ok(Object data, String className, String methodName, long total, List<?> rows) {
        return new BSLResult(200, "OK", data, className, methodName, total, rows);
    }
    
    public static BSLResult ok(long total, List<?> rows, String className, String methodName) {
        return new BSLResult(200, "OK",  total, rows, className, methodName);
    }
    
    public static BSLResult ok(long total, List<?> rows) {
        return new BSLResult(total, rows);
    }

    public static BSLResult ok() {
        return new BSLResult(null);
    }

    public BSLResult() {

    }

    public static BSLResult build(Integer status, String msg) {
        return new BSLResult(status, msg, null);
    }

    public BSLResult(Integer status, String msg, Object data, String className, String methodName) {
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.className = className;
		this.methodName = methodName;
	}

	public BSLResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public BSLResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    
    public BSLResult(long total, List<?> rows) {
        this.status = 200;
        this.msg = "OK";
        this.total = total;
        this.rows = rows;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

	public Integer getStatus() {
        return status;
    }

    public BSLResult(Integer status, String msg, Object data, String className,
		String methodName, long total, List<?> rows) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.className = className;
		this.methodName = methodName;
		this.total = total;
		this.rows = rows;
	}
    
    public BSLResult(Integer status, String msg,long total, List<?> rows, String className,
    		String methodName) {
    		super();
    		this.status = status;
    		this.msg = msg;
    		this.className = className;
    		this.methodName = methodName;
    		this.total = total;
    		this.rows = rows;
    	}

	public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为BSLResult对象
     * 
     * @param jsonData json数据
     * @param clazz BSLResult中的object类型
     * @return
     */
    public static BSLResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, BSLResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static BSLResult format(String json) {
        try {
            return MAPPER.readValue(json, BSLResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static BSLResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

	public static ObjectMapper getMapper() {
		return MAPPER;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "BSLResult [status=" + status + ", msg=" + msg + ", total="+total+",data="
				+ data + ", className=" + className + ", methodName="
				+ methodName + "]";
	}
    
}

