/*

This interface defines the non-primitive objects that will be used in the UI.

*/

export interface HobbyInterface {
  hobbyName: string;
  isChecked: Boolean;
}

export interface Course {
  name: string;
  description: string;
  duration: number;
  fees: number;
  uniName: string;
}
