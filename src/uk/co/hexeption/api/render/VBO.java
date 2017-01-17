package uk.co.hexeption.api.render;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

/**
 * Created by Hexeption on 17/01/2017.
 */
public class VBO {

    protected int id;

    protected FloatBuffer vertices = null;

    protected int vertexCount = 0, vertexLenght = 3;

    public VBO() {

        id = GLUtils.genVBO();
    }

    public VBO create(float[] vertices) {

        return create(vertices, 3);
    }

    public VBO create(float[] vertices, int vertexLenght) {

        this.vertices = BufferUtils.createFloatBuffer(vertices.length);
        for (float vertexPoint : vertices) {
            this.vertices.put(vertexPoint);
        }

        this.vertices.flip();
        this.vertexCount = vertices.length / vertexLenght;
        this.vertexLenght = vertexLenght;
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, this.vertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return this;
    }

    public void render(int mode) {

        glBindBuffer(GL_ARRAY_BUFFER, id);
        glVertexPointer(vertexLenght, GL_FLOAT, 0, 0);
        glEnableClientState(GL_VERTEX_ARRAY);
        glDrawArrays(mode, 0, vertexCount);
        glDisableClientState(GL_VERTEX_ARRAY);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public FloatBuffer getVertices() {

        return vertices;
    }

    public void setVertices(FloatBuffer vertices) {

        this.vertices = vertices;
    }

    public int getVertexCount() {

        return vertexCount;
    }

    public void setVertexCount(int vertexCount) {

        this.vertexCount = vertexCount;
    }

    public int getVertexLenght() {

        return vertexLenght;
    }

    public void setVertexLenght(int vertexLenght) {

        this.vertexLenght = vertexLenght;
    }
}
