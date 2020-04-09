package com.rfbsoft.v0.managers.collator;

import com.rfbsoft.v0.managers.locale.LocaleGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.text.Collator;

@Configuration
public class LocaleCollator {
    @Autowired
    @Qualifier("localeGetterWithEnv")
    LocaleGetter getter;

    public Collator getLocaleCollator() {
        return Collator.getInstance(getter.getLocale());
    }
}
