/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.bpmn2.xml;

import org.jbpm.process.core.Work;
import org.drools.core.xml.ExtensibleXmlParser;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.HumanTaskNode;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SoupeUserTaskHandler extends UserTaskHandler {
     protected void handleNode(final Node node, final Element element, final String uri,
                              final String localName, final ExtensibleXmlParser parser) throws SAXException {
        super.handleNode(node, element, uri, localName, parser);
        HumanTaskNode humanTaskNode = (HumanTaskNode) node;
        Work work = humanTaskNode.getWork();
        work.setName("Human Task");
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                org.w3c.dom.Node attribute = attributes.item(i);
                if (!attribute.getNodeName().equals("name")) {
                    humanTaskNode.getWork().setParameter(attribute.getNodeName(), attribute.getNodeValue());
                }
            }
        }
         org.w3c.dom.Node xmlNode = element.getFirstChild();
        while (xmlNode != null) {
            String nodeName = xmlNode.getNodeName();
             if ("extensionElements".equals(nodeName)) {
                getInputOutputParamters(xmlNode, humanTaskNode);
                break;
            }
             xmlNode = xmlNode.getNextSibling();
        }
    }
     private void getInputOutputParamters(org.w3c.dom.Node element, HumanTaskNode humanTaskNode) {
        org.w3c.dom.Node xmlNode = element.getFirstChild();
        while (xmlNode != null) {
            String nodeName = xmlNode.getNodeName();
             if ("inputOutput".equals(nodeName)) {
                NodeList childNodes = xmlNode.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    org.w3c.dom.Node childNode = childNodes.item(i);
                    if ("inputParameter".equals(childNode.getNodeName())) {
                        humanTaskNode.getWork().setParameter(
                                childNode.getAttributes().getNamedItem("name").getNodeValue(),
                                childNode.getNodeValue());
                    }
                }
                 break;
            }
             xmlNode = xmlNode.getNextSibling();
        }
    }
 }