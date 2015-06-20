package models;

import java.util.List;

public class Items {
	
	String kind;
	String title;
	String htmlTitle;
	String link;
	String displayLink;
	String snippet;
	String htmlSnippet;
	List<Metatag> metatags;
	Pagemap pagemap;
	
	public Items(){
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtmlTitle() {
		return htmlTitle;
	}

	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDisplayLink() {
		return displayLink;
	}

	public void setDisplayLink(String displayLink) {
		this.displayLink = displayLink;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getHtmlSnippet() {
		return htmlSnippet;
	}

	public void setHtmlSnippet(String htmlSnippet) {
		this.htmlSnippet = htmlSnippet;
	}

	public List<Metatag> getMetatags() {
		return metatags;
	}

	public void setMetatags(List<Metatag> metatags) {
		this.metatags = metatags;
	}
	
	
	
}
