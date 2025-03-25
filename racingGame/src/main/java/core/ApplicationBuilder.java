package core;

import java.awt.Dimension;

public class ApplicationBuilder {
	private String title;
	private Dimension dimension;
	
	public ApplicationBuilder() {
	}
	
	public ApplicationBuilder title(String title) {
		this.title = title;
		return this;
	}
	
	public ApplicationBuilder dimention(Dimension dimension) {
		this.dimension = dimension;
		return this;
	}
	
	public Application build() {
		return new Application(title, dimension);
	}
}
