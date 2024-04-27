package com.mygdx.spinning;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class spin extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Sprite sprite;
    private float originalY;
    private float velocityY = 0;
    private boolean jumping = false;

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer = new ShapeRenderer();

        spriteBatch = new SpriteBatch();

        Texture texture = new Texture("assets/mario.png");

        sprite = new Sprite(texture);
        sprite.setPosition(100, 100);
        sprite.setSize(100,100);

        originalY = sprite.getY();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5f, 0.7f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(0, 0, viewport.getWorldWidth(), 100);
        shapeRenderer.end();

        float gravity = -300;
        velocityY += gravity * Gdx.graphics.getDeltaTime();
        sprite.translateY(velocityY * Gdx.graphics.getDeltaTime());

        if (sprite.getY() < originalY) {
            sprite.setY(originalY);
            velocityY = 0;
            jumping = false;
        }

        handleInput();

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    private void handleInput() {
        float movementSpeed = 200;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.setX(sprite.getX() - movementSpeed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.setX(sprite.getX() + movementSpeed * Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !jumping) {
            velocityY = 300;
            jumping = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }
}
