package com.mygdx.spinning;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class spin extends ApplicationAdapter {
	private ShapeRenderer shapeRenderer;
	private Viewport viewport;

	@Override
	public void create() {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.5f, 0.7f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		viewport.apply();

		shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BROWN);
		shapeRenderer.rect(0, 0, viewport.getWorldWidth(), 80);
		shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
}
