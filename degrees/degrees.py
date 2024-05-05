import csv
import sys

from util import Node, StackFrontier, QueueFrontier

# Maps names to a set of corresponding person_ids
names = {}

# Maps person_ids to a dictionary of: name, birth, movies (a set of movie_ids)
people = {}

# Maps movie_ids to a dictionary of: title, year, stars (a set of person_ids)
movies = {}


def load_data(directory):
    """
    Load data from CSV files into memory.
    """
    # Load people
    with open(f"{directory}/people.csv", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            people[row["id"]] = {
                "name": row["name"],
                "birth": row["birth"],
                "movies": set()
            }
            if row["name"].lower() not in names:
                names[row["name"].lower()] = {row["id"]}
            else:
                names[row["name"].lower()].add(row["id"])

    # Load movies
    with open(f"{directory}/movies.csv", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            movies[row["id"]] = {
                "title": row["title"],
                "year": row["year"],
                "stars": set()
            }

    # Load stars
    with open(f"{directory}/stars.csv", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            try:
                people[row["person_id"]]["movies"].add(row["movie_id"])
                movies[row["movie_id"]]["stars"].add(row["person_id"])
            except KeyError:
                pass


def main():
    if len(sys.argv) > 2:
        sys.exit("Usage: python degrees.py [directory]")
    directory = sys.argv[1] if len(sys.argv) == 2 else "small"

    # Load data from files into memory
    print("Loading data...")
    load_data(directory)
    print("Data loaded.")

    source = person_id_for_name(input("Name: "))
    if source is None:
        sys.exit("Person not found.")
    target = person_id_for_name(input("Name: "))
    if target is None:
        sys.exit("Person not found.")

    path = shortest_path(source, target)

    if path is None:
        print("Not connected.")
    else:
        degrees = len(path)
        print(f"{degrees} degrees of separation.")
        path = [(None, source)] + list(path)
    for i in range(degrees):
        movie_id, person_id = path[i + 1]
        movie = movies[movie_id]["title"]
        person1 = people[path[i][1]]["name"]
        person2 = people[person_id]["name"]
        print(f"{i + 1}: {person1} and {person2} starred in {movie}")

def shortest_path(source, target):
    # """
    # Returns the shortest list of (movie_id, person_id) pairs
    # that connect the source to the target.
    #
    # If no possible path, returns None.
    # """
    #
    # # Initialize frontier to just the starting position
    # start = Node(state=source, parent=None, action=None)
    # frontier = QueueFrontier()
    # frontier.add(start)
    #
    # # Initialize an empty explored set
    # explored = set()
    #
    # # Keep looping until solution found
    # while True:
    #
    #     # If nothing left in frontier, then no path
    #     if frontier.empty():
    #         return None
    #
    #     # Choose a node from the frontier
    #     node = frontier.remove()
    #
    #     # If node is the goal, then we have a solution
    #     if node.state == target:
    #         actions = []
    #         cells = []
    #         while node.parent is not None:
    #             actions.append(node.action)
    #             cells.append(node.state)
    #             node = node.parent
    #         actions.reverse()
    #         cells.reverse()
    #         print(actions, cells)
    #         return actions, cells
    #
    #     # Mark node as explored
    #     explored.add(node.state)
    #
    #     # Add neighbors to frontier
    #     for action, state in neighbors_for_person(node.state):
    #         if not frontier.contains_state(state) and state not in explored:
    #             child = Node(state=state, parent=node, action=action)
    #             frontier.add(child)
    #


    """
       Returns the shortest list of (movie_id, person_id) pairs
       that connect the source to the target.

       If no possible path, returns None.
       """

    # TODO
    # Initialize frontier
    source_node = Node(source,None,None)
    frontier = QueueFrontier()
    frontier.add(source_node)

    # Initialize explored set
    explored = set()

    targetNode = Node(None,None,None)
    #BFS algorithem
    while(True):
        if frontier.empty():
            return None
        nextNode = frontier.remove()
        explored.add(nextNode.state)
        if nextNode.state == target :
            targetNode = nextNode
            break
        neighbors = neighbors_for_person(nextNode.state)
        for neighborhood_relation in neighbors :
            if not frontier.contains_state(neighborhood_relation[1]) and neighborhood_relation[1] not in explored :
                frontier.add(Node(neighborhood_relation[1],nextNode,neighborhood_relation[0]))

    # put the shortest path in the list
    shortest_path_list = []
    while(True):
        if targetNode.state == source :
            break
        else:
            shortest_path_list.append((targetNode.action,targetNode.state))
        targetNode = targetNode.parent

    shortest_path_list.reverse()

    return shortest_path_list

    raise NotImplementedError


def person_id_for_name(name):
    """
    Returns the IMDB id for a person's name,
    resolving ambiguities as needed.
    """
    person_ids = list(names.get(name.lower(), set()))
    if len(person_ids) == 0:
        return None
    elif len(person_ids) > 1:
        print(f"Which '{name}'?")
        for person_id in person_ids:
            person = people[person_id]
            name = person["name"]
            birth = person["birth"]
            print(f"ID: {person_id}, Name: {name}, Birth: {birth}")
        try:
            person_id = input("Intended Person ID: ")
            if person_id in person_ids:
                return person_id
        except ValueError:
            pass
        return None
    else:
        return person_ids[0]


def neighbors_for_person(person_id):
    """
    Returns (movie_id, person_id) pairs for people
    who starred with a given person.
    """
    movie_ids = people[person_id]["movies"]
    neighbors = set()
    for movie_id in movie_ids:
        for person_id in movies[movie_id]["stars"]:
            neighbors.add((movie_id, person_id))
    return neighbors


if __name__ == "__main__":
    main()
