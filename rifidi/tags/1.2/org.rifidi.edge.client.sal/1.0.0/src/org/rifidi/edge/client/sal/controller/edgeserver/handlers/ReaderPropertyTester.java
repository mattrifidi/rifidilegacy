/**
 * 
 */
package org.rifidi.edge.client.sal.controller.edgeserver.handlers;
//TODO: Comments
import org.eclipse.core.expressions.PropertyTester;
import org.rifidi.edge.client.model.sal.RemoteReader;

/**
 * @author kyle
 * 
 */
public class ReaderPropertyTester extends PropertyTester {

	/**
	 * 
	 */
	public ReaderPropertyTester() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 * java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		if (receiver instanceof RemoteReader) {
			RemoteReader reader = (RemoteReader) receiver;
			if (property.equals("dirty")) {
				Boolean expected = (Boolean) expectedValue;
				return expected.equals(reader.isDirty());
			}
		}
		return false;
	}

}
