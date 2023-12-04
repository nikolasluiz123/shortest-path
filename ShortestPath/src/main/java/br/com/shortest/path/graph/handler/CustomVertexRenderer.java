package br.com.shortest.path.graph.handler;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import br.com.shortest.path.graph.structure.Edge;
import br.com.shortest.path.graph.structure.Vertex;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

public class CustomVertexRenderer implements Renderer.Vertex<Vertex, Edge> {

    @Override
    public void paintVertex(RenderContext<Vertex, Edge> rc, Layout<Vertex, Edge> layout, Vertex vertex) {
        GraphicsDecorator graphicsContext = rc.getGraphicsContext();
        Point2D p = layout.apply(vertex);

        int width = 80;
        int height = 80;

        Shape backgroundCircle = new Ellipse2D.Double(p.getX() - width / 2, p.getY() - height / 2, width, height);
        graphicsContext.setPaint(Color.WHITE);
        graphicsContext.fill(backgroundCircle);

        graphicsContext.setPaint(Color.BLACK);
        graphicsContext.draw(backgroundCircle);

        FontMetrics fm = graphicsContext.getFontMetrics();
        Rectangle2D labelBounds = fm.getStringBounds(vertex.getValue(), graphicsContext.getDelegate());
        graphicsContext.drawString(vertex.getValue(), 
        						  (float) (p.getX() - labelBounds.getWidth() / 2),
            					  (float) (p.getY() + labelBounds.getHeight() / 4));
    }

}
