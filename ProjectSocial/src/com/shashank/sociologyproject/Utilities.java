package com.shashank.sociologyproject;
//package com.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
//
//import com.Feilds.Doc;
//import com.Feilds.EventDate;
//import com.Feilds.Injured;
//import com.Feilds.Killed;
//import com.filechoser.CodeSheet;
//import com.filechoser.FileChoser;

public class Utilities {

	public Doc createDoc(String[] paragraphs, CodeSheet codesheet) {
		Doc doc = new Doc();
		doc.setTranslater(codesheet);
		for (int i = 0; i < paragraphs.length; i++) {
			if (paragraphs[i].trim().contains("TITLE")) {
				String title = "";

				while (!(paragraphs[i].trim().contains("EVENT’S START "))) {
					i++;
					if (!(paragraphs[i].trim().contains("EVENT’S START ")))
						title = title.concat(paragraphs[i].trim());

				}

				doc.setTitle(title);
			}

			if (paragraphs[i].trim().contains("EVENT’S START ")) {
				String eventStart = "";
				EventDate eventdate = new EventDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
				Date convertedDate = new Date();
				while (!paragraphs[i].trim().contains("Length of Protest")) {
					if (paragraphs[i].startsWith("Day")
							|| paragraphs[i].startsWith("(L")
							|| paragraphs[i].trim().contains("EVENT’S START ")
							|| (paragraphs[i].trim().length() == 0))
						i++;
					else {

						eventdate.setStartDayOfTheWeek(paragraphs[i].trim());
						i++;
						/*try {
							if (!(paragraphs[i].trim().equals("") || paragraphs[i]
									.trim().contains("Length of Protest") || paragraphs[i]
											.trim().contains("sd"))) {
								convertedDate = dateFormat.parse(paragraphs[i]
										.trim());
							} else
								convertedDate = null;

						} catch (Exception e) {
							e.printStackTrace();
						}*/
						// System.out.println("Converted string to date : " +
						// convertedDate);
						eventdate.setStartDate(null);
						i++;
						eventdate.setEndDayOfTheWeek(paragraphs[i].trim());
						i++;
						try {
							if (!(paragraphs[i].trim().equals("") || paragraphs[i]
									.trim().contains("Length of Protest"))) {
								convertedDate = dateFormat.parse(paragraphs[i]
										.trim());
							} else
								convertedDate = null;
						} catch (Exception e) {
							e.printStackTrace();
						}
						// System.out.println("Converted string to date : " +
						// convertedDate);
						eventdate.setEndDate(convertedDate);
						// System.out.println("line is"+paragraphs[i].trim());

					}
				}
				doc.setEventdate(eventdate);
			}
			if (paragraphs[i].trim().contains("Length of Protest ")) {
				String lop = "";
				while (!paragraphs[i].trim().contains("Country")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Length of Protest")
							|| (paragraphs[i].trim().contains("Check one:")))
						i++;
					else {
						if (!(paragraphs[i].trim().contains("Country")))
							lop = lop.concat(paragraphs[i].trim());
						i++;

					}
				}
				doc.setLengthOfProtest(lop);
			}
			if (paragraphs[i].trim().contains("Country")) {

				while (!paragraphs[i].trim().contains("State/Province")) {
					if ((paragraphs[i].trim().length() == 0))
						i++;
					else {
						doc.setCountry(paragraphs[i].substring(paragraphs[i]
								.indexOf(":") + 1));
						i++;
					}
				}
			}
			if (paragraphs[i].contains("State/Province")) {
				String state = "";
				while (!paragraphs[i].trim().contains("Location")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains("State/Province"))
						i++;
					else {
						state = state.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setState(state);

			}
			if (paragraphs[i].contains("Location")) {
				String loc = "";
				while (!paragraphs[i].trim().contains(
						"Event/Action Description")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains("Location"))
						i++;
					else {
						loc = loc.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setLocation(loc);

			}
			if (paragraphs[i].contains("Event/Action Description")) {
				String event = "";
				while (!paragraphs[i].trim().contains("Protesting")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Event/Action Description"))
						i++;
					else {
						event = event.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setEventDescription(event);

			}
			if (paragraphs[i].contains("Protesting")) {
				String protesting = "";
				while (!paragraphs[i].trim().contains("Self Definition ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains("Protesting"))
						i++;
					else {
						protesting = protesting.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setProtestingParties(protesting);

			}
			if (paragraphs[i].contains("Self Definition")) {
				String definition = "";
				while (!paragraphs[i].trim().contains("Issue/Grievance ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains("Self Definition"))
						i++;
					else {
						definition = definition.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setSelfDefinition(definition);

			}
			if (paragraphs[i].contains("Issue/Grievance")) {
				String issue = "";
				while (!paragraphs[i].trim().contains("Protest Target")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains("Issue/Grievance"))
						i++;
					else {
						issue = issue.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setIssues(issue);

			}
			if (paragraphs[i].contains("Protest Target")) {
				String target = "";
				while (!paragraphs[i].trim().contains("Target’s Responding ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains("Protest Target"))
						i++;
					else {
						target = target.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setProtestTarget(target);

			}
			if (paragraphs[i].contains("Target’s Responding ")) {
				String targetResponding = "";
				while (!paragraphs[i].trim().contains(
						"Protesting Organizations ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| (paragraphs[i].trim().startsWith("How"))
							|| (paragraphs[i].trim().startsWith("information"))
							|| paragraphs[i].trim().contains(
									"Target’s Responding "))
						i++;
					else {
						targetResponding = targetResponding
								.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setTargetResponding(targetResponding);

			}
			if (paragraphs[i].contains("Protesting Organizations")) {
				String protestingOrganization = "";
				while (!paragraphs[i].trim().contains("Number of Protesters")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Protesting Organizations"))
						i++;
					else {
						protestingOrganization = protestingOrganization
								.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setProtestingOrganization(protestingOrganization);

			}
			if (paragraphs[i].contains("Number of Protesters")) {
				String nop = "";
				while (!paragraphs[i].trim().contains(
						"Connection to Other Events")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Number of Protesters"))
						i++;
					else {
						nop = nop.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setNumberOfProtestors(nop);

			}
			if (paragraphs[i].contains("Connection to Other Events")) {
				String connection = "";
				while (!paragraphs[i].trim().contains("Immediate Outcome")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Connection to Other Events"))
						i++;
					else {
						connection = connection.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setConnectionToOtherEvents(connection);

			}

			if (paragraphs[i].contains("Immediate Outcome ")) {
				String outcome = "";
				while (!paragraphs[i].trim().contains("Comments")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Immediate Outcome"))
						i++;
					else {
						outcome = outcome.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setOutcome(outcome);

			}
			if (paragraphs[i].contains("Comments")) {
				String comments = "";
				while (!paragraphs[i].trim().contains("Reported State Forces")) {
					if ((paragraphs[i].trim().length() == 0)
							|| (paragraphs[i].trim().contains("**"))
							|| paragraphs[i].trim().contains("Comments")|| paragraphs[i].trim().contains("STATE INVOLVEMENT/VIOLENCE"))
						i++;
					else {
						comments = comments.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setComments(comments);

			}
			if (paragraphs[i].contains("Reported State Forces")) {
				String rsf = "";
				while (!paragraphs[i].trim().contains("Number of State Force ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Reported State Forces"))
						i++;
					else {
						rsf = rsf.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setStateForceAction(rsf);

			}
			if (paragraphs[i].contains("Number of State Force")) {
				String nsf = "";
				while (!paragraphs[i].trim().contains("Number of Protesters Arrested")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Number of State Force"))
						i++;
					else {
						nsf = nsf.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setNumberOfStateForce(nsf);

			}
			if (paragraphs[i].contains("Number of Protesters Arrested")) {
				String protestors = "";
				while (!paragraphs[i].trim().contains("Number Injured ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().contains(
									"Number of Protesters Arrested"))
						i++;
					else {
						protestors = protestors.concat(paragraphs[i].trim());
						i++;
					}
				}
				doc.setNumberOfProtestorArrested(protestors);

			}
			if (paragraphs[i].contains("Number Injured ")) {
				Injured injured = new Injured();
				while (!paragraphs[i].trim().contains("Number Killed ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().startsWith("Protesters")
							|| paragraphs[i].trim().contains("Number Injured"))
						i++;
					else {
						injured.setProtestors(codesheet.translate(paragraphs[i]
								.trim()));
						i++;
						injured.setStateForces(codesheet
								.translate(paragraphs[i].trim()));
						i++;
						injured.setOthers(codesheet.translate(paragraphs[i]
								.trim()));
						i++;
					}

				}

				doc.setNumberInjured(injured);

			}
			if (paragraphs[i].contains("Number Killed ")) {
				Killed killed = new Killed();
				while (!paragraphs[i].trim().contains("Property Damage ")) {
					if ((paragraphs[i].trim().length() == 0)
							|| paragraphs[i].trim().startsWith("Protesters")
							|| paragraphs[i].trim().contains("Number Killed"))
						i++;
					else {
						killed.setProtestors(codesheet.translate(paragraphs[i]
								.trim()));
						i++;
						killed.setStateForces(codesheet.translate(paragraphs[i]
								.trim()));
						i++;
						killed.setOthers(codesheet.translate(paragraphs[i]
								.trim()));
						i++;
					}

				}

				doc.setNumberKilled(killed);

			}
			if (paragraphs[i].contains("Property Damage ")) {
				String property = new String();

				if ((paragraphs[i].trim().length() == 0)
						|| paragraphs[i].trim().contains("Property Damage"))
					i++;
				else {
					property = paragraphs[i].trim();
				}

				doc.setPropertyDamage(property);

			}
		}

		return doc;
	}

	public static void printHash() {
		HashMap<String, Doc> hmp = new HashMap<String, Doc>();
		hmp = FileChoser.getMainHmp();
		Iterator iterator = hmp.keySet().iterator();

		// codesheet.
		// codesheet.g
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			Doc value = (Doc) hmp.get(key);
System.out.println("******************************************************************************************************************************");
			System.out.println("File Name==" + key);
			System.out.println("values:-----> ");
			System.out.println("title==" + value.getTitle());
			System.out.println(("Start day of the week==" + value.getEventdate().getStartDayOfTheWeek()));
			System.out.println(("Start Date==" + value.getEventdate().getStartDate()));
			System.out.println(("End Date==" + value.getEventdate().getEndDate()));
			System.out.println(("End day of the week==" + value.getEventdate().getEndDayOfTheWeek()));
			System.out.println(("lengthOfProtest==" + value
					.getLengthOfProtest()));
			System.out.println("country==" + value.getCountry());
			System.out.println("state==" + value.getState());

			System.out.println(("location==" + value.getLocation()));
			System.out.println(("eventDescription==" + value
					.getEventDescription()));
			System.out.println("protestingParties=="
					+ value.getProtestingParties());
			System.out.println("selfDefinition==" + value.getSelfDefinition());

			System.out.println("issues==" + value.getIssues());
			System.out.println(("protestTarget==" + value.getProtestTarget()));
			System.out.println(("targetResponding==" + value
					.getTargetResponding()));
			System.out.println("protestingOrganization=="
					+ value.getProtestingOrganization());
			System.out.println("numberOfProtestors=="
					+ value.getNumberOfProtestors());
			System.out.println(("connectionToOtherEvents==" + value
					.getConnectionToOtherEvents()));
			System.out.println(("outcome==" + value.getOutcome()));
			System.out.println("comments==" + value.getComments());
			System.out.println("stateForceAction=="
					+ value.getStateForceAction());

			System.out.println("numberOfStateForce=="
					+ value.getNumberOfStateForce());
			System.out.println(("numberOfProtestorArrested==" + value
					.getNumberOfProtestorArrested()));
			System.out.println(("Protestors Injured==" + value
					.getNumberInjured().getProtestors()));
			System.out.println(("State Forces Injured==" + value
					.getNumberInjured().getStateForces()));
			System.out.println(("Others Injured==" + value.getNumberInjured()
					.getOthers()));

			System.out.println("Protesters Killed=="
					+ value.getNumberKilled().getProtestors());
			System.out.println("State Forces Killed=="
					+ value.getNumberKilled().getStateForces());
			System.out.println("Others Killed=="
					+ value.getNumberKilled().getOthers());

			System.out.println("propertyDamage==" + value.getPropertyDamage());

		}
	}
}
