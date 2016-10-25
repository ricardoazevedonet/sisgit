/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.sp.tce.sisgit.infraestrutura.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Classe utilitaria para retornar mensagens do arquivo msgBundle
 * @author A7EB
 */
public class ResourceBundleUtil {
    private static final Map<Locale, ResourceBundle> mapBundles = new HashMap<Locale, ResourceBundle>();
    
    private ResourceBundleUtil() {}

    /**
     *
     * @param chave
     * @return Mensagem com o locale padrao
     */
    public static String getString(final String key) {
        return getString(key, Locale.getDefault());
    }

    /**
     *
     * @param key
     * @param locale
     * @return Mensagem com o locale passado no parametro
     */
    public static String getString(final String key, final Locale locale) {
        ResourceBundle rb = mapBundles.get(locale);
        if (rb == null) {
            rb = ResourceBundle.getBundle("br.gov.sp.tce.sisgit.apresentacao.resources", locale);
            mapBundles.put(locale, rb);
        }
        return rb.getString(key);
    }
}
