package lv.lottery.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:security.properties")
public class SecurityPropertiesBean {
        @Value("${auth.admin.username:defaultAdmin}")
        private String adminName;

        @Value("${auth.admin.password}")
        private String adminPassword;

        public String getAdminName() {
            return adminName;
        }

        public String getAdminPassword() {
            return adminPassword;
        }
}
