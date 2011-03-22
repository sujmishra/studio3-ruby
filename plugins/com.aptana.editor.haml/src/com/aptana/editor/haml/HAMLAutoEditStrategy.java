/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.haml;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import com.aptana.editor.common.text.RubyRegexpAutoIndentStrategy;
import com.aptana.editor.haml.preferences.IPreferenceConstants;

public class HAMLAutoEditStrategy extends RubyRegexpAutoIndentStrategy
{

	public HAMLAutoEditStrategy(String contentType, SourceViewerConfiguration configuration, ISourceViewer sourceViewer)
	{
		super(contentType, configuration, sourceViewer);
	}

	@Override
	protected boolean shouldAutoIndent()
	{
		return HAMLEditorPlugin.getDefault().getPreferenceStore().getBoolean(IPreferenceConstants.HAML_AUTO_INDENT);
	}

}