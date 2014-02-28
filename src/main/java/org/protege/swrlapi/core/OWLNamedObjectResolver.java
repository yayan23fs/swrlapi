package org.protege.swrlapi.core;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.protege.owl.portability.model.OWLAnnotationPropertyAdapter;
import org.protege.owl.portability.model.OWLClassAdapter;
import org.protege.owl.portability.model.OWLDataPropertyAdapter;
import org.protege.owl.portability.model.OWLDatatypeAdapter;
import org.protege.owl.portability.model.OWLNamedIndividualAdapter;
import org.protege.owl.portability.model.OWLObjectPropertyAdapter;
import org.protege.swrlapi.core.arguments.SWRLClassBuiltInArgument;
import org.protege.swrlapi.core.arguments.SWRLDataPropertyBuiltInArgument;
import org.protege.swrlapi.core.arguments.SWRLIndividualBuiltInArgument;
import org.protege.swrlapi.core.arguments.SWRLObjectPropertyBuiltInArgument;
import org.protege.swrlapi.exceptions.TargetRuleEngineException;

/**
 * This class is used by rule engine implementations to determine the type of OWL entities using their prefixed name. It
 * also maps from prefixed names to URIs for those entities.
 */
public class OWLNamedObjectResolver
{
	private final Map<String, URI> prefixedName2URIMap;
	private final Set<String> classPrefixedNames;
	private final Set<String> individualPrefixedNames;
	private final Set<String> objectPropertyPrefixedNames;
	private final Set<String> dataPropertyPrefixedNames;
	private final Set<String> annotationPropertyPrefixedNames;
	private final Set<String> datatypePrefixedNames;

	public OWLNamedObjectResolver()
	{
		this.prefixedName2URIMap = new HashMap<String, URI>();
		this.classPrefixedNames = new HashSet<String>();
		this.individualPrefixedNames = new HashSet<String>();
		this.objectPropertyPrefixedNames = new HashSet<String>();
		this.dataPropertyPrefixedNames = new HashSet<String>();
		this.annotationPropertyPrefixedNames = new HashSet<String>();
		this.datatypePrefixedNames = new HashSet<String>();
	}

	public void reset()
	{
		this.prefixedName2URIMap.clear();
		this.classPrefixedNames.clear();
		this.individualPrefixedNames.clear();
		this.objectPropertyPrefixedNames.clear();
		this.dataPropertyPrefixedNames.clear();
		this.annotationPropertyPrefixedNames.clear();
		this.datatypePrefixedNames.clear();
	}

	public boolean isOWLClass(String prefixedName)
	{
		return this.classPrefixedNames.contains(prefixedName);
	}

	public boolean isOWLNamedIndividual(String prefixedName)
	{
		return this.individualPrefixedNames.contains(prefixedName);
	}

	public boolean isOWLObjectProperty(String prefixedName)
	{
		return this.objectPropertyPrefixedNames.contains(prefixedName);
	}

	public boolean isOWLDataProperty(String prefixedName)
	{
		return this.dataPropertyPrefixedNames.contains(prefixedName);
	}

	public boolean isOWLAnnotationProperty(String prefixedName)
	{
		return this.annotationPropertyPrefixedNames.contains(prefixedName);
	}

	public boolean isOWLDatatype(String prefixedName)
	{
		return this.datatypePrefixedNames.contains(prefixedName);
	}

	public void record(OWLClassAdapter cls)
	{
		recordPrefixedName2URIMapping(cls.getPrefixedName(), cls.getURI());

		this.classPrefixedNames.add(cls.getPrefixedName());
	}

	public void record(OWLNamedIndividualAdapter individual)
	{
		recordPrefixedName2URIMapping(individual.getPrefixedName(), individual.getURI());

		this.individualPrefixedNames.add(individual.getPrefixedName());
	}

	public void record(OWLObjectPropertyAdapter property)
	{
		recordPrefixedName2URIMapping(property.getPrefixedName(), property.getURI());

		this.objectPropertyPrefixedNames.add(property.getPrefixedName());
	}

	public void record(OWLDataPropertyAdapter property)
	{
		recordPrefixedName2URIMapping(property.getPrefixedName(), property.getURI());

		this.dataPropertyPrefixedNames.add(property.getPrefixedName());
	}

	public void record(OWLAnnotationPropertyAdapter property)
	{
		recordPrefixedName2URIMapping(property.getPrefixedName(), property.getURI());

		this.annotationPropertyPrefixedNames.add(property.getPrefixedName());
	}

	public void record(OWLDatatypeAdapter datatype)
	{
		recordPrefixedName2URIMapping(datatype.getPrefixedName(), datatype.getURI());

		this.datatypePrefixedNames.add(datatype.getPrefixedName());
	}

	public void record(SWRLClassBuiltInArgument classArgument)
	{
		recordPrefixedName2URIMapping(classArgument.getPrefixedName(), classArgument.getURI());
		this.classPrefixedNames.add(classArgument.getPrefixedName());
	}

	public void record(SWRLIndividualBuiltInArgument individualArgument)
	{
		recordPrefixedName2URIMapping(individualArgument.getPrefixedName(), individualArgument.getURI());
		this.individualPrefixedNames.add(individualArgument.getPrefixedName());
	}

	public void record(SWRLObjectPropertyBuiltInArgument propertyArgument)
	{
		recordPrefixedName2URIMapping(propertyArgument.getPrefixedName(), propertyArgument.getURI());
		this.objectPropertyPrefixedNames.add(propertyArgument.getPrefixedName());
	}

	public void record(SWRLDataPropertyBuiltInArgument propertyArgument)
	{
		recordPrefixedName2URIMapping(propertyArgument.getPrefixedName(), propertyArgument.getURI());
		this.dataPropertyPrefixedNames.add(propertyArgument.getPrefixedName());
	}

	public void recordPrefixedName2URIMapping(String prefixedName, URI uri)
	{
		if (!this.prefixedName2URIMap.containsKey(prefixedName))
			this.prefixedName2URIMap.put(prefixedName, uri);
	}

	public URI prefixedName2URI(String prefixedName) throws TargetRuleEngineException
	{
		if (this.prefixedName2URIMap.containsKey(prefixedName))
			return this.prefixedName2URIMap.get(prefixedName);
		else
			throw new TargetRuleEngineException("internal error: could not find URI for prefixed name " + prefixedName);
	}
}
