package com.bsl.common.pojo;

import java.util.List;

/**
 * 将数据转化为easyui可识别的json格式
 * @author huangyl
 * @date 2019年5月26日  
 *
 */
public class TreeNode {
	private String id;
	private String text;
	private String href;
	private String type;
	private List<TreeNode> children;//孩子节点的List
	public TreeNode(String id, String text, String href, String type) {
		super();
		this.id = id;
		this.text = text;
		this.href = href;
		this.type = type;
	}
	public TreeNode(String id, String text, String href, String type, List<TreeNode> children) {
		super();
		this.id = id;
		this.text = text;
		this.href = href;
		this.type = type;
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public String getHref() {
		return href;
	}
	public String getType() {
		return type;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", href=" + href + ", type=" + type + ", children=" + children
				+ "]";
	}
}
