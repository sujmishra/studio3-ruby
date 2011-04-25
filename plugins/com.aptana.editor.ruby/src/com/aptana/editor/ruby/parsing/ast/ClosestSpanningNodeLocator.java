package com.aptana.editor.ruby.parsing.ast;

import org.jrubyparser.ast.Node;

// Visitor to find the closest node that spans a given offset that satisfies a given condition.
// 
// @author Jason Morrison
public class ClosestSpanningNodeLocator extends AbstractNodeLocator
{
	private Node locatedNode;
	private int offset;
	private INodeAcceptor acceptor;

	// Finds the closest spanning node given offset that is accepted by the acceptor.
	//
	// +rootNode+
	// Root Node that contains all nodes to search.
	// +offset+
	// Offset to search for
	// +acceptor+
	// block returning boolean on whether node fulfills conditions to be able to be selected
	public Node find(Node root_node, int offset, INodeAcceptor acceptor)
	{
		if (root_node == null)
		{
			return null;
		}

		this.locatedNode = null;
		this.offset = offset;
		this.acceptor = acceptor;

		root_node.accept(this);

		// Return the match
		return this.locatedNode;
	}

	@Override
	protected Object handleNode(Node node)
	{
		if (this.acceptor.accepts(node))
		{
			if (spansOffset(node, this.offset)
					&& (this.locatedNode == null || (spanLength(node) <= spanLength(this.locatedNode))))
			{
				this.locatedNode = node;
			}
		}

		return super.handleNode(node);
	}
}
