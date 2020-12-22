package com.shashibhagat.crm_app_employee;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.shashibhagat.crm_app_employee");

        noClasses()
            .that()
            .resideInAnyPackage("com.shashibhagat.crm_app_employee.service..")
            .or()
            .resideInAnyPackage("com.shashibhagat.crm_app_employee.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.shashibhagat.crm_app_employee.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
