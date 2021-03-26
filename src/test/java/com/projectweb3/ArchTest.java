package com.projectweb3;

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
            .importPackages("com.projectweb3");

        noClasses()
            .that()
            .resideInAnyPackage("com.projectweb3.service..")
            .or()
            .resideInAnyPackage("com.projectweb3.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.projectweb3.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
