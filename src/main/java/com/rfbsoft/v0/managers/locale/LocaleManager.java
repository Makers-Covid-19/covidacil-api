package com.rfbsoft.v0.managers.locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration("localeGetterWithEnv")
public class LocaleManager implements LocaleGetter {
    @Value("${locale.selected.locale}")
    private String selectedlocele;

    public Locale getLocale() {
        return new Locale(selectedlocele);
    }
}
