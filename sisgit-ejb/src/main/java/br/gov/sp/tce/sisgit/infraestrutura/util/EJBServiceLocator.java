package br.gov.sp.tce.sisgit.infraestrutura.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.gov.sp.tce.sisgit.infraestrutura.exception.InfraestruturaRuntimeException;

/**
 *
 * @author A7GG
 */
public class EJBServiceLocator {

	private static InitialContext context;

	/*@SuppressWarnings("unchecked")
	public static <T> T lookup(Class<T> clazz, String jndi) throws InfraestruturaRuntimeException {
        try {
            if (context == null) {
                context = new InitialContext();
            }
            return (T) PortableRemoteObject.narrow(context.lookup(jndi + "#" + clazz.getCanonicalName()), clazz);
        } catch (NamingException e) {
            throw new InfraestruturaRuntimeException();
        }
    }*/




	@SuppressWarnings("unchecked")
	public static <T> T lookup(Class<T> clazz, String jndi) throws InfraestruturaRuntimeException {
		@SuppressWarnings("rawtypes")
		final Hashtable jndiProperties = jndiProperties();  
		
		try {

			if (context == null) {
				context  = new InitialContext(jndiProperties);
			}

			final String viewClassName = clazz.getName();

		
			String jndix = jndi+ "!" + viewClassName;

			return (T) context.lookup(jndix);
		} catch (NamingException e) {
			throw new InfraestruturaRuntimeException();
		}
	}

	
	private static Hashtable<String, String> jndiProperties() {
		
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
		return jndiProperties;
	}

	@SuppressWarnings("unchecked")
	public static <T> T lookup(String jndi) throws InfraestruturaRuntimeException {
		
		final Hashtable<String, String> jndiProperties = jndiProperties();  
		
		try {

			if (context == null) {
				context  = new InitialContext(jndiProperties);
			}

			return (T) context.lookup(jndi);
		} catch (NamingException e) {
			throw new InfraestruturaRuntimeException();
		}
		
	}

}
