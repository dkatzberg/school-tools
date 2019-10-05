module schoolTools {
	exports de.dkatzberg.schooltools.common;
	exports de.dkatzberg.schooltools.grades.calculate;
	exports de.dkatzberg.schooltools.common.config;
	exports de.dkatzberg.schooltools.grades.latex;
	exports de.dkatzberg.schooltools.start;
	exports de.dkatzberg.schooltools.grades;
	exports de.dkatzberg.schooltools.common.gui;
	exports de.dkatzberg.schooltools.grades.domain;
	exports de.dkatzberg.schooltools.common.domain;

	requires javafx.base;
	requires javafx.controls;
	requires transitive javafx.graphics;
	requires org.junit.jupiter.api;
}