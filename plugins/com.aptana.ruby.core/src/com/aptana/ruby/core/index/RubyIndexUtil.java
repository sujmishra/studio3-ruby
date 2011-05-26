package com.aptana.ruby.core.index;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;

import com.aptana.index.core.Index;
import com.aptana.index.core.IndexManager;
import com.aptana.ruby.internal.core.index.CoreStubber;
import com.aptana.ruby.launching.RubyLaunchingPlugin;

public abstract class RubyIndexUtil
{

	/**
	 * Returns the project index, ruby core index, the std lib indices and the gem indices.
	 * 
	 * @param project
	 * @return
	 */
	public synchronized static Collection<Index> allIndices(IProject project)
	{
		// TODO Cache this set per project?
		Collection<Index> indices = new ArrayList<Index>();
		Index index = getIndex(project);
		if (index != null)
		{
			indices.add(index);
		}
		// TODO Inline this method from CoreStubber here!
		indices.add(CoreStubber.getRubyCoreIndex(project));
		indices.addAll(getStdLibIndices(project));
		indices.addAll(getGemIndices(project));
		return indices;
	}

	/**
	 * Return the project's index.
	 * 
	 * @param project
	 * @return
	 */
	public static Index getIndex(IProject project)
	{
		if (project == null)
		{
			return null;
		}
		return IndexManager.getInstance().getIndex(project.getLocationURI());
	}

	public static Collection<Index> getStdLibIndices(IProject project)
	{
		// TODO Inline this method here!
		return CoreStubber.getStdLibIndices(project);
	}

	protected static Collection<Index> getGemIndices(IProject project)
	{
		Collection<Index> indices = new ArrayList<Index>();
		for (IPath path : RubyLaunchingPlugin.getGemPaths(project))
		{
			indices.add(IndexManager.getInstance().getIndex(path.toFile().toURI()));
		}
		return indices;
	}

}
