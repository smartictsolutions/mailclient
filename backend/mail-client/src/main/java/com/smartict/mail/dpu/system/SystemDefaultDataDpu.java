/* SmartICT Bilisim A.S. (C) 2020 */
package com.smartict.mail.dpu.system;

import com.smartict.mail.dpu.management.DpuStarterEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class SystemDefaultDataDpu implements ApplicationListener<DpuStarterEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemDefaultDataDpu.class);

    private final ApplicationContext appContext;

    public SystemDefaultDataDpu(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Override
    public void onApplicationEvent(@NonNull DpuStarterEvent event) {
        try {
            // SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            // securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(Constants.ADMIN_USER_ID.toString(), "N/A", null));
            // SecurityContextHolder.setContext(securityContext);

            // this.createDefaultRoles();
        } catch (Exception e) {
            e.printStackTrace();
            SpringApplication.exit(this.appContext, () -> 1);
            System.exit(1);
        }
    }
}
